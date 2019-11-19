package com.issac.bfxy.paya.service.impl;

import com.issac.bfxy.paya.entity.CustomAccount;
import com.issac.bfxy.paya.mapper.CustomAccountMapper;
import com.issac.bfxy.paya.service.PayService;
import com.issac.bfxy.paya.service.producer.CallbackService;
import com.issac.bfxy.paya.service.producer.TransactionProducer;
import com.issac.bfxy.paya.util.FastJsonConvertUtil;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@Service
public class PayServiceImpl implements PayService {

    public static final String TX_PA_TOPIC = "tx_pay_topic";
    public static final String TAGS = "pay";
    public static final String KEYS = "tx_pay_keys";

    @Autowired
    private CustomAccountMapper customAccountMapper;

    @Autowired
    TransactionProducer producer;

    @Autowired
    CallbackService callbackService;

    @Override
    public String payment(String userId, String orderId, String accountId, double money) {
        String paymentResult = "";
        try {
            // 最开始有一步token验证操作(重复提单问题)
            BigDecimal payMoney = new BigDecimal(money);
            // 加锁开始（获取）
            CustomAccount old = customAccountMapper.selectByPrimaryKey(accountId);
            BigDecimal currentBalance = old.getCurrentBalance();
            int currentVersion = old.getVersion();
            // 业务发出
            // 当前一个用户账户 只允许一个线程（一个应用端访问）
            // 技术出发
            // 1 redis 去重 分布式锁
            // 2 数据库乐观锁去重

            // 要对大概率时间进行提前预判（小概率时间我们做放过,最后保障数据的一致性即可）
            // 做扣款操作的时候，获得分布式锁，看一下能否获得
            BigDecimal newBalance = currentBalance.subtract(payMoney);

            // 加锁结束

            // 或者一种情况获取锁失败，也应该放行
            if (newBalance.doubleValue() > 0) {
                // 1. 组装消息->payb消息
                String keys = UUID.randomUUID().toString() + "$" + System.currentTimeMillis();
                Map<String, Object> params = new HashMap<>();
                params.put("userId", userId);
                params.put("orderId", orderId);
                params.put("accountId", accountId);
                params.put("money", money);

                Message message = new Message(
                        TX_PA_TOPIC,
                        TAGS,
                        KEYS,
                        FastJsonConvertUtil.convertObjectToJSON(params).getBytes()
                );
                // 可能需要的参数
                params.put("payMoney", payMoney);
                params.put("newBalance", newBalance);
                params.put("currentVersion", currentVersion);
                // 消息发送并且本地事务执行
                // 同步阻塞
                CountDownLatch countDownLatch = new CountDownLatch(1);
                params.put("currentCountDown", countDownLatch);

                TransactionSendResult sendResult = producer.sendMessage(message, params);
                // 1. 执行本地事务

                countDownLatch.await();
                if (sendResult.getSendStatus() == SendStatus.SEND_OK
                        && sendResult.getLocalTransactionState() == LocalTransactionState.COMMIT_MESSAGE) {
                    // 回调order通知支付成功消息
                    callbackService.sendOKMessage(orderId, userId);
                    paymentResult = "支付成功！";
                } else {
                    paymentResult = "支付失败！";
                }
            } else {
                paymentResult = "余额不足！";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            paymentResult = "支付失败！";
        }
        return paymentResult;
    }
}
