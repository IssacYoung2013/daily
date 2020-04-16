package com.issac.interview.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class SyncThread implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.startsWith("A")) {
            async();
        } else if (threadName.startsWith("B")) {
            syncObjectBlock1();
        } else if (threadName.startsWith("C")) {
            syncObjectMethod1();
        }else if (threadName.startsWith("D")) {
            syncClassBlock1();
        } else if (threadName.startsWith("E")) {
            syncClassMethod1();
        }
    }

    private synchronized void syncObjectMethod1() {
        try {
            System.out.println(Thread.currentThread().getName()
                    + " syncObjectMethod1:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            System.out.println(Thread.currentThread().getName()
                    + "_syncObjectMethod1_Start:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()
                    + "_syncObjectMethod1_End:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized static void syncClassMethod1() {
        try {
            System.out.println(Thread.currentThread().getName()
                    + " syncClassMethod1:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            System.out.println(Thread.currentThread().getName()
                    + "_syncClassMethod1_Start:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()
                    + "_syncClassMethod1_End:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void syncObjectBlock1() {
        try {
            System.out.println(Thread.currentThread().getName()
                    + "syncObjectBlock1:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()
                        + "_syncObjectBlock1_Start:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()
                        + "_syncObjectBlock1_End:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void syncClassBlock1() {
        try {
            System.out.println(Thread.currentThread().getName()
                    + "syncClassBlock1:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()
                        + "_syncClassBlock1_Start:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()
                        + "_syncClassBlock1_End:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void async() {
        try {
            System.out.println(Thread.currentThread().getName()
                    + "_Async_Start:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()
                    + "_Async_End:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
