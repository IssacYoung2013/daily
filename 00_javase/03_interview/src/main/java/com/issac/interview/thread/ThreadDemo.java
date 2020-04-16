package com.issac.interview.thread;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class ThreadDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("Thread1");
        MyThread myThread2 = new MyThread("Thread2");
        MyThread myThread3 = new MyThread("Thread3");

        myThread.start();
        myThread2.start();
        myThread3.start();
    }
}
