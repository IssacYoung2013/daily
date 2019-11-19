package com.dialog.mq.rocketmq.simple;

import com.dialog.mq.rocketmq.constant.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 *
 *
 * @author: ywy
 * @date: 2019-09-29
 * @desc:
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // create producer with group name
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name_1");
        // specify name server address
        producer.setNamesrvAddr(Const.NAME_SERVER);
        // launch the instance
        producer.start();
        for (int i = 0; i < 100; i++) {
            // create message
            Message message = new Message(
                    "test-topic",
                    "tagA",
                    ("Hello RocketMQ").getBytes()
            );
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();

    }
}
