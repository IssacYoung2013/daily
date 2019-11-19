package com.issac.bfxy.payb.service.consumer;

import com.issac.bfxy.payb.util.FastJsonConvertUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: ywy
 * @date: 2019-11-18
 * @desc:
 */
@Component
public class PkgOrderlyConsumer {

    private DefaultMQPushConsumer consumer;

    private static final String NAMESERVER = "127.0.0.1:9876";

    private static final String CONSUMER_GROUP_NAME = "pkg_orderly_consumer_group_name";

    public static final String PKG_TOPIC = "pkg_topic";

    public static final String PKG = "pkg";

    private PkgOrderlyConsumer() {
        try {
            this.consumer = new DefaultMQPushConsumer(CONSUMER_GROUP_NAME);
            this.consumer.setConsumeThreadMin(10);
            this.consumer.setConsumeThreadMax(30);
            this.consumer.setNamesrvAddr(NAMESERVER);
            this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            this.consumer.subscribe(PKG_TOPIC, PKG);
            this.consumer.registerMessageListener(new MessageListenerOrderly4Pkg());
            this.consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    class MessageListenerOrderly4Pkg implements MessageListenerOrderly {

        Random random = new Random();

        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
            for (MessageExt msg :
                    msgs) {
                try {
                    String topic = msg.getTopic();
                    String msgBody = new String(msg.getBody(), "utf-8");
                    String tags = msg.getTags();
                    String keys = msg.getKeys();
                    System.err.println("收到事务消息, topic: " + topic + ", tags: " + tags + ", keys: " + keys);

                    String orignMsgId = msg.getProperties().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
                    System.err.println("orignMsgId: " + orignMsgId);

                    //通过keys 进行去重表去重 或者使用redis进行去重???? --> 不需要

                    Map<String, Object> body = FastJsonConvertUtil.convertJSONToObject(msgBody, Map.class);
                    String orderId = (String) body.get("orderId");
                    String userId = (String) body.get("userId");
                    String txt = (String) body.get("txt");

                    // 模拟实际业务耗时
                    TimeUnit.SECONDS.sleep(random.nextInt(3) + 1);
                    System.out.println("业务操作：" + txt);
                    // 创建包裹信息
                    // 物流服务调用（调度系统-快递员系统）

                } catch (Exception e) {
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }
            return ConsumeOrderlyStatus.SUCCESS;
        }
    }
}
