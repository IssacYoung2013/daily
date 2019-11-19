package com.dialog.mq.rocketmq.order;

import com.dialog.mq.rocketmq.constant.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author: ywy
 * @date: 2019-09-29
 * @desc:
 */
public class OrderProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name_2");
        producer.setNamesrvAddr(Const.NAME_SERVER);
        producer.start();

        String[] tags = new String[]{
                "TagA", "TagB", "TagC", "TagD", "TagE"
        };
        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;
            Message message = new Message(
                    "test-topic",
                    tags[i % tags.length],
                    "KEY" + i,
                    ("Hello RocketMQ" + i).getBytes()
            );

            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);
            System.out.println(sendResult);
        }
        producer.shutdown();

    }
}
