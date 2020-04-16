package com.issac.interview.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask).start();
        if(!futureTask.isDone()) {
            System.out.println("task has not finished,please wait!");
        }
        System.out.println("task return "+ futureTask.get());
    }
}
