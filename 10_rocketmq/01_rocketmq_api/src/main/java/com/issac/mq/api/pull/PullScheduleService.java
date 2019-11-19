package com.issac.mq.api.pull;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 *
 * @author: ywy
 * @date: 2019-11-14
 * @desc:
 */
public class PullScheduleService {
    public static void main(String[] args) throws MQClientException {
        String groupName = "test_pull_consumer_name";
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService(groupName);
        scheduleService.getDefaultMQPullConsumer().setNamesrvAddr(Const.NAMESRV_ADDR);
        scheduleService.setMessageModel(MessageModel.CLUSTERING);
        scheduleService.registerPullTaskCallback("test_pull_topic", new PullTaskCallback() {
            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {
                MQPullConsumer consumer = context.getPullConsumer();
                System.out.println("queueId:"+mq.getQueueId());
                try {
                    // 获取从哪里拉取
                    long offset = consumer.fetchConsumeOffset(mq, false);
                    if(offset < 0) {
                        offset =0;
                    }
                    PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                    handlePullResult(pullResult);
                    consumer.updateConsumeOffset(mq,pullResult.getNextBeginOffset());
                    // 设置 3000ms后重新拉取
                    context.setPullNextDelayTimeMillis(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        scheduleService.start();
    }

    static void handlePullResult(PullResult pullResult) {
        switch (pullResult.getPullStatus()) {
            case FOUND:
                List<MessageExt> list = pullResult.getMsgFoundList();
                list.forEach(messageExt -> System.out.println(new String(messageExt.getBody())));
                break;
            case NO_NEW_MSG:
                System.out.println("没有新数据");
                break;
            case OFFSET_ILLEGAL:
            case NO_MATCHED_MSG:
            default:
                break;
        }
    }
}
