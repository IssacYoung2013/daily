package com.issac.interview.thread;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class RunnableDemo {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable("Runnable1");
        MyRunnable myRunnable2 = new MyRunnable("Runnable2");
        MyRunnable myRunnable3 = new MyRunnable("Runnable3");

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable2);
        Thread t3 = new Thread(myRunnable3);

        t1.start();
        t2.start();
        t3.start();
    }
}
