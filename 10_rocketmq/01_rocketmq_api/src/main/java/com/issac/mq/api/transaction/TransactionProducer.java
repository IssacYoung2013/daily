package com.issac.mq.api.transaction;

import com.issac.mq.api.constant.Const;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("test_tx_producer_name");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("test_tx_producer_name-check-thread");
                        return thread;
                    }
                });
        producer.setNamesrvAddr(Const.NAMESRV_ADDR);
        producer.setExecutorService(executorService);
        // 这个对象主要做两件事情，第一件事情就是异步执行本地事务，另一个是回查功能
        TransactionListener transactionListener = new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);
        producer.start();

        Message message = new Message(
                "test_tx_topic",
                "TagA",
                "Key",
                ("Hello RocketMQ 4 TX!").getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        producer.sendMessageInTransaction(message, "我是回调的参数");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        //        producer.shutdown();
    }
}
