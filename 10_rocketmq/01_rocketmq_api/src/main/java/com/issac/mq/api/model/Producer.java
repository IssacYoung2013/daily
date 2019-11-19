package com.issac.mq.api.model;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-14
 * @desc:
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        String groupName = "test_model_producer_name";
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(Const.NAMESRV_ADDR);
        producer.start();

        for (int i = 0; i < 8; i++) {
            try {
                Message message = new Message(
                        "test_model_topic_2",
                        i%2==0?"TagA":"TagB",
//                        "TagA",
                        ("信息内容"+i).getBytes()
                );
                SendResult send = producer.send(message);
                System.out.println(send);
            }
            catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();
    }
}
