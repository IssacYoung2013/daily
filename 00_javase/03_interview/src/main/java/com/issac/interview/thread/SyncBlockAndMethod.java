package com.issac.interview.thread;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class SyncBlockAndMethod {

    public void syncsTask() {
        synchronized (this) {
            System.out.println("hello");
        }
    }

    public synchronized void syncTask() {
        System.out.println("Hello Again");
    }
}
