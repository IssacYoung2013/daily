package com.issac.mq.api.model;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-11-14
 * @desc:
 */
public class Consumer1 {

    public Consumer1() {
        try {
            String groupName = "test_model_consumer_name_1";
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
            consumer.setNamesrvAddr(Const.NAMESRV_ADDR);
            consumer.subscribe("test_model_topic_2", "TagA||TagB");
//            consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.registerMessageListener(new Listener());
            consumer.start();
            System.out.println("c1 start...");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListenerConcurrently {
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            try {
                for (MessageExt messageExt : msgs) {
                    String topic = messageExt.getTopic();
                    String body = new String(messageExt.getBody(), "utf8");
                    String tags = messageExt.getTags();
//                    if("TagA".equalsIgnoreCase(tags)) {
                        System.out.println("Get Message topic:" + topic + " body:" + body
                                + " tag:" + tags);
//                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

    public static void main(String[] args) {
        new Consumer1();
    }
}
