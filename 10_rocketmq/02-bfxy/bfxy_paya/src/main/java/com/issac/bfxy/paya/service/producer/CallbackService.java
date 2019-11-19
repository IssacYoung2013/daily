package com.issac.bfxy.paya.service.producer;

import com.issac.bfxy.paya.util.FastJsonConvertUtil;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@Service
public class CallbackService {

    public static final String CALLBACK_PAY_TOPIC = "callback_pay_topic";

    public static final String CALLBACK_PAY_TAGS = "callback_pay_tags";

    public static final String NAMESERVER = "127.0.0.1:9876";

    @Autowired
    SyncProducer syncProducer;

    public void sendOKMessage(String orderId,String userId) {
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("orderId",orderId);
        params.put("status","2");

        String keys = UUID.randomUUID().toString() + "$" + System.currentTimeMillis();
        Message message = new Message(
                CALLBACK_PAY_TOPIC,
                CALLBACK_PAY_TAGS,
                keys,
                FastJsonConvertUtil.convertObjectToJSON(params).getBytes()
        );

        SendResult sendResult = syncProducer.sendMessage(message);
    }

}
