package com.issac.processor;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author: ywy
 * @date: 2019-11-05
 * @desc:
 */
public class SubscribeTest {

    public static void main(String[] args) throws InterruptedException {
        // 创建发布者
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        // 创建订阅者
        SomeSubscriber subscriber = new SomeSubscriber();
        // 创建处理器
        SomeProcessor processor = new SomeProcessor();
        // 建立订阅关系
        publisher.subscribe(processor);
        processor.subscribe(subscriber);
        // 生产并发布消息
        for (int i = 0; i < 300; i++) {
            int item = new Random().nextInt(100);
            // 阻塞方法，发布消息，有限缓冲区满了之后方法
            System.out.println("生产出第"+i+"条消息");
            // 阻塞，发布者不具有无限缓冲区
            publisher.submit(item);
        }
        // 关闭发布者
        publisher.close();

        TimeUnit.SECONDS.sleep(10);
    }
}
