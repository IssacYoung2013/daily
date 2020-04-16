package com.issac.interview.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ywy
 * @date: 2020-01-03
 * @desc:
 */
public class ReentrantLockDemo implements Runnable {

    private static ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread t1 = new Thread(reentrantLockDemo);
        Thread t2 = new Thread(reentrantLockDemo);

        t1.start();
        t2.start();
    }
}
