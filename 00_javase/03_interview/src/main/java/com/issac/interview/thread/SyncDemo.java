package com.issac.interview.thread;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class SyncDemo {
    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread a1 = new Thread(syncThread,"A1");
        Thread a2 = new Thread(syncThread,"A2");
        Thread b1 = new Thread(syncThread,"B1");
        Thread b2 = new Thread(syncThread,"B2");
        Thread c1 = new Thread(syncThread,"C1");
        Thread c2 = new Thread(syncThread,"C2");
        Thread d1 = new Thread(new SyncThread(),"D1");
        Thread d2 = new Thread(new SyncThread(),"D2");
        Thread e1 = new Thread(new SyncThread(),"E1");
        Thread e2 = new Thread(new SyncThread(),"E2");
        a1.start();
        a2.start();
        b1.start();
        b2.start();
        c1.start();
        c2.start();
        d1.start();
        d2.start();
        e1.start();
        e2.start();

    }
}
