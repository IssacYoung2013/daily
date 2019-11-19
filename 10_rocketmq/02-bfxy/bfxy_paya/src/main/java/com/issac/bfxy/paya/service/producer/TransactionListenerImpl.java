package com.issac.bfxy.paya.service.producer;

import com.issac.bfxy.paya.mapper.CustomAccountMapper;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@Component
public class TransactionListenerImpl implements TransactionListener {

    @Autowired
    private CustomAccountMapper customAccountMapper;

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("执行本地事务单元");
        CountDownLatch countDownLatch = null;
        try {
            Map<String, Object> params = (Map<String, Object>) arg;
            String userId = (String) params.get("userId");
            String orderId = (String) params.get("orderId");
            String accountId = (String) params.get("accountId");
            // 当前支付款
            BigDecimal money = (BigDecimal) params.get("payMoney");
            // 前置扣款成功的余额
            BigDecimal newBalance = (BigDecimal) params.get("newBalance");
            int currentVersion = (int) params.get("currentVersion");
            countDownLatch = (CountDownLatch) params.get("currentCountDown");
            Date currentTime = new Date();
            int count = this.customAccountMapper.updateBalance(accountId, newBalance, currentVersion, currentTime);
            if (count == 1) {
                countDownLatch.countDown();
                return LocalTransactionState.COMMIT_MESSAGE;
            } else {
                countDownLatch.countDown();
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            countDownLatch.countDown();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return null;
    }
}
