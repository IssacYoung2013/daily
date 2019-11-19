package com.dialog.mq.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;

/**
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, IOException {
        DefaultMQProducer producer = new DefaultMQProducer("produce_group_name");
        producer.setNamesrvAddr("localhost:9876");

        /**
         * 接收 Broker 回调的消息
         */
//        producer.setCallbackExecutor();
        /**
         * 压缩消息
         */
//        producer.setCompressMsgBodyOverHowmuch();
//        producer.createTopic();
//        producer.setDefaultTopicQueueNums();
//        producer.setHeartbeatBrokerInterval();
//        producer.setMaxMessageSize();
//        producer.setNotAvailableDuration();
//        producer.setPersistConsumerOffsetInterval();
//        producer.setProducerGroup();
//        producer.setRetryAnotherBrokerWhenNotStoreOK();
//        producer.setRetryTimesWhenSendAsyncFailed();

        producer.start();

        for (int i = 0; i < 1; i++) {
            Message message = new Message("test-topic", "tagA", "keyA"+i, ("hello rocketmq"+i).getBytes());
//            SendResult sendResult = producer.send(message);

            // 异步发消息
            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    // 对数据变更操作，保证100%投靠
                    System.out.println("msgId:" + sendResult.getMsgId() + ",status:"+sendResult.getSendStatus());
                }

                public void onException(Throwable throwable) {
                    System.out.println("发送失败");
                }
            });
        }

        System.out.println("message sent!");
        System.in.read();
        producer.shutdown();

    }
}
