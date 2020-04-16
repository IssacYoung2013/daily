package com.issac.interview.thread;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class ThreadTest {
    public static void attack() {
        System.out.println("Fight");
        System.out.println("Current Thread:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                attack();
            }
        };
        System.out.println("Current Thread:" + Thread.currentThread().getName());

        thread.run();
        thread.start();
        thread.join(500);
        thread.start();

    }
}
