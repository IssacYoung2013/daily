package com.dialog.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group-name");
        consumer.setNamesrvAddr("localhost:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("test-topic", "*");

//        consumer.setConsumeThreadMax(20);
        consumer.setConsumeTimeout(1000);
        consumer.setMaxReconsumeTimes(2);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                System.out.println(list);
                MessageExt messageExt = list.get(0);
                try {
                    String keys = messageExt.getKeys();
                    if (keys.equals("keyA1")) {
                        System.err.println("消费失败");
                        int a = 1 / 0;
                    }
                    System.out.println(new String(messageExt.getBody()));
                } catch (Exception ex) {
                    int reconsumeTimes = messageExt.getReconsumeTimes();
                    System.out.println("reconsumeTimes:" + reconsumeTimes);
                    if (reconsumeTimes == 3) {
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();


    }
}
