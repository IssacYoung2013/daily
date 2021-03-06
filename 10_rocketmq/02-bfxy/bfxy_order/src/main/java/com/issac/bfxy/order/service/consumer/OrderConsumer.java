package com.issac.bfxy.order.service.consumer;

import com.issac.bfxy.order.constant.OrderStatus;
import com.issac.bfxy.order.mapper.OrderMapper;
import com.issac.bfxy.order.service.OrderService;
import com.issac.bfxy.order.util.FastJsonConvertUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@Component
public class OrderConsumer {

    private DefaultMQPushConsumer consumer;

    public static final String CALLBACK_PAY_TOPIC = "callback_pay_topic";

    public static final String CALLBACK_PAY_TAGS = "callback_pay_tags";

    public static final String NAMESERVER = "127.0.0.1:9876";

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    public OrderConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer("callback_pay_consumer_group");
        consumer.setConsumeThreadMin(10);
        consumer.setConsumeThreadMax(50);
        consumer.setNamesrvAddr(NAMESERVER);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(CALLBACK_PAY_TOPIC, CALLBACK_PAY_TAGS);
        consumer.registerMessageListener(new MessageListenerConcurrently4Pay());
        consumer.start();
    }

    private class MessageListenerConcurrently4Pay implements MessageListenerConcurrently {
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            MessageExt msg = msgs.get(0);
            try {
                String topic = msg.getTopic();
                String msgBody = new String(msg.getBody(), "utf-8");
                String tags = msg.getTags();
                String keys = msg.getKeys();
                System.err.println("收到消息：" + "  topic :" + topic + "  ,tags : " + tags + "keys :" + keys + ", msg : " + msgBody);
                String orignMsgId = msg.getProperties().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
                System.err.println("orignMsgId: " + orignMsgId);

                //通过keys 进行去重表去重 或者使用redis进行去重???? --> 不需要

                Map<String, Object> body = FastJsonConvertUtil.convertJSONToObject(msgBody, Map.class);
                String orderId = (String) body.get("orderId");
                String userId = (String) body.get("userId");
                String status = (String)body.get("status");

                Date currentTime = new Date();

                if(status.equals(OrderStatus.ORDER_PAYED.getStatus())) {
                    int count  = orderMapper.updateOrderStatus(orderId, status, "admin", currentTime);
                    if(count == 1) {
                        orderService.sendOrderlyMessage4Pkg(userId, orderId);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }
}
