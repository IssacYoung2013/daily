package com.dialog.mq.rocketmq.simple;

import com.dialog.mq.rocketmq.constant.Const;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author: ywy
 * @date: 2019-09-29
 * @desc:
 */
public class AsyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        producer.setNamesrvAddr(Const.NAME_SERVER);
        producer.start();

        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            Message message = new Message(
                    "test-topic",
                    "TagA",
                    "OrderID188",
                    ("Hello RocketMQ").getBytes()
            );

            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.println(index + " OK " + sendResult.getMsgId());
                }

                public void onException(Throwable e) {
                    System.out.println(index + " Exception " + e.getMessage());
                }
            });
        }
        producer.shutdown();
    }
}
