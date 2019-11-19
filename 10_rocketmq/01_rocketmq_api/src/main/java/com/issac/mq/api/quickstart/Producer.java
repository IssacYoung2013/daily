package com.issac.mq.api.quickstart;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: ywy
 * @date: 2019-11-07
 * @desc:
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");
        producer.setNamesrvAddr(Const.NAMESRV_ADDR);

        producer.start();

//        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            // 1. 创建消息
            Message message = new Message(
                    // 主题
                    "test_quick_topic",
                    // 标签
                    "TagA",
                    // 用户自定义key
                    "KeyA" + i,
                    ("Hello RocketMQ" + i).getBytes()
            );
            // 2.1 延迟发送消息
//            if (i == 1) {
//                message.setDelayTimeLevel(3);
//            }
            // 2. 同步发送消息
//            SendResult sendResult = producer.send(message);
//            System.out.println(sendResult);

            // 2.2 指定消息队列
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer queueNumber = (Integer) arg;
                    return mqs.get(queueNumber);

                }
            }, 2);
            System.out.println(sendResult);


//            // 2.2 异步
//            producer.send(message, new SendCallback() {
//
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.println("msgId:" + sendResult.getMsgId() + ",status:"
//                            + sendResult.getSendStatus());
//                    latch.countDown();
//                }
//
//                @Override
//                public void onException(Throwable e) {
//                    e.printStackTrace();
//                    System.out.println("发送失败");
//                }
//            });
        }
//        latch.await();
        // 3. 关闭
        producer.shutdown();
    }
}
