package com.issac.mq.api.orderly;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public class Consumer {

    public Consumer() throws Exception {
        String groupName = "test_orderly_consumer_name";
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR);

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("test_order_topic", "TagA");
        consumer.registerMessageListener(new Listener());
        consumer.start();
        System.out.println("consumer started .");
    }

    public static void main(String[] args) throws Exception {
        new Consumer();
    }

    class Listener implements MessageListenerOrderly {

        private Random random = new Random();

        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
            // 设置自动提交
            context.setAutoCommit(true);
            for (MessageExt msg : msgs) {
                System.out.println("queueId:" +msg.getQueueId() + ",content:"+ new String(msg.getBody()));
                try {
                    // 模拟业务逻辑
                    TimeUnit.SECONDS.sleep(random.nextInt(4) + 1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return ConsumeOrderlyStatus.SUCCESS;
        }
    }
}
