package com.issac.mq.api.pull;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author: ywy
 * @date: 2019-11-14
 * @desc:
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        String groupName = "test_pull_producer_name";
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(Const.NAMESRV_ADDR);
        producer.start();
        for (int i = 0; i < 10; i++) {
            try {
                Message message = new Message(
                        "test_pull_topic",
                        "TagA",
                        ("Hello RocketMQ"+i).getBytes()
                );
                SendResult send = producer.send(message);
                System.out.println(send);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(3000);
            }
        }
        producer.shutdown();
    }
}
