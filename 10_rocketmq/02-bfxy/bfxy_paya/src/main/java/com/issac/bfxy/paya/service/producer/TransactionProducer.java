package com.issac.bfxy.paya.service.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@Component
public class TransactionProducer implements InitializingBean {

    private TransactionMQProducer producer;

    private ExecutorService executorService;

    @Autowired
    private TransactionListener transactionListener;

    public static final String NAMESERVER = "127.0.0.1:9876";

    public static final String PRODUCER_GROUP_NAME = "tx_pay_producer_group_name";

    private TransactionProducer() {
        this.producer = new TransactionMQProducer(PRODUCER_GROUP_NAME);
        this.executorService = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName(PRODUCER_GROUP_NAME + "-check-thread");
                        return thread;
                    }
                }
        );
        this.producer.setExecutorService(executorService);
        this.producer.setNamesrvAddr(NAMESERVER);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.producer.setTransactionListener(transactionListener);
        start();
    }

    private void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        this.producer.shutdown();
    }

    public TransactionSendResult sendMessage(Message message, Object argument) {
        TransactionSendResult sendResult = null;
        try {
            sendResult = this.producer.sendMessageInTransaction(message,argument);
        } catch (Exception ex) {

        }
        return sendResult;
    }
}
