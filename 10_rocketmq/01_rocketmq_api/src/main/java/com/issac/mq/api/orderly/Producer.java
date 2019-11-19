package com.issac.mq.api.orderly;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        try {
            String groupName = "test_orderly_group_name";
            DefaultMQProducer producer = new DefaultMQProducer(groupName);
            producer.setNamesrvAddr(Const.NAMESRV_ADDR);

            producer.start();

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String dateStr = simpleDateFormat.format(date);
            // 五条消息是一个大的业务操作
            for (int i = 0; i < 5; i++) {
                // 时间戳
                String body = dateStr + " Hello RocketMQ " + i;
                // 发送数据：如果使用顺序消费，则必须实现MessageQueueSelector，保证消息进入同一个队列中去
                Message message = new Message(
                        "test_order_topic",
                        "TagA",
                        "KEY" + i,
                        body.getBytes()
                );
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        System.out.println("id:" + id);
                        return mqs.get(id);
                    }
                }, 1);
                // 1 是队列下标
                System.out.println(sendResult + ",body" + body);
            }
            for (int i = 0; i < 5; i++) {
                // 时间戳
                String body = dateStr + " Hello RocketMQ " + i;
                // 发送数据：如果使用顺序消费，则必须实现MessageQueueSelector，保证消息进入同一个队列中去
                Message message = new Message(
                        "test_order_topic",
                        "TagA",
                        "KEY" + i,
                        body.getBytes()
                );
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        System.out.println("id:" + id);
                        return mqs.get(id);
                    }
                }, 2);
                // 1 是队列下标
                System.out.println(sendResult + ",body" + body);
            }
            for (int i = 0; i < 5; i++) {
                // 时间戳
                String body = dateStr + " Hello RocketMQ " + i;
                // 发送数据：如果使用顺序消费，则必须实现MessageQueueSelector，保证消息进入同一个队列中去
                Message message = new Message(
                        "test_order_topic",
                        "TagA",
                        "KEY" + i,
                        body.getBytes()
                );
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        System.out.println("id:" + id);
                        return mqs.get(id);
                    }
                }, 3);
                // 1 是队列下标
                System.out.println(sendResult + ",body" + body);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }
}
