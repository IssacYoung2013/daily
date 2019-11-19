package com.issac.processor;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-05
 * @desc: 订阅者
 */
public class SomeSubscriber implements Flow.Subscriber<String> {

    /**
     * 声明订阅令牌
     */
    private Flow.Subscription subscription;

    /**
     * 当发布者第一次发布消息
     * @param subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        // 设置订阅者首次向令牌订阅消息的数量
        this.subscription.request(10);
    }

    @Override
    public void onNext(String item) {
        System.out.println("当前订阅者正在消费的消息为：" + item);
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
        }
        this.subscription.request(10);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    /**
     * 当令牌中的所有消息全部消费完毕
     */
    @Override
    public void onComplete() {
        System.out.println("所有消息消费完毕");
    }
}
