package com.issac.mq.api.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public class TransactionListenerImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("--- 执行本地事务 ---");
        String callArgs = (String) arg;
        System.out.println("callArgs:"+ callArgs);
        System.out.println("msg:"+msg);
        // tx.begin
        // 数据库落库操作
        // tx.commit
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("----回调消息检查----"+ msg);
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
