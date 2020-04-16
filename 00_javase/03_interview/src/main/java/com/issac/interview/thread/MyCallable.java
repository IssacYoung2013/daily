package com.issac.interview.thread;

import java.util.concurrent.Callable;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        String value = "test";
        System.out.println("Ready to work");
        Thread.sleep(5000);
        System.out.println("Work Done");
        return value;
    }
}
