package com.issac.mq.api.pull;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: ywy
 * @date: 2019-11-14
 * @desc:
 */
public class PullConsumer {

    /**
     * key 为指定队列，value 为队列拉取数据的最后位置
     */
    public static final Map<MessageQueue, Long> offsetTable = new HashMap<>();

    public static void main(String[] args) throws MQClientException {
        String groupName = "test_pull_consumer_name";
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(groupName);
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR);
        consumer.start();
        System.out.println("consumer start");
        // 从指定topic获取所有的队列
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("test_pull_topic");
        // 遍历每个队列，拉取数据
        mqs.forEach(mq -> {
            System.out.println("Consume from the queue" + mq);
            SINGLE_MQ:
            while (true) {
                try {
                    // 从queue拉取数据，从什么位置开始，默认拉取32条
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    System.out.println(pullResult);
                    System.out.println(pullResult.getPullStatus());
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    PullScheduleService.handlePullResult(pullResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.shutdown();
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetTable.get(mq);
        if (offset != null) {
            return offset;
        }
        return 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long nextBeginOffset) {
        offsetTable.put(mq,nextBeginOffset);
    }


}
