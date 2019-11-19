package com.issac.processor;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author: ywy
 * @date: 2019-11-05
 * @desc:
 */
public class SomeProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(10);
    }

    /**
     * 将发布者发布的大于50的过滤，小于50的转字符串发布
     *
     * @param item
     */
    @Override
    public void onNext(Integer item) {
        System.out.println("当前订阅者正在处理的消息为：" + item);
        if (item < 50) {
            this.submit("该处理结果" + String.valueOf(item));
        }
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

    @Override
    public void onComplete() {
        System.out.println("所有消息处理完毕");
    }
}
