package com.issac.interview.jvm.gc;

import java.lang.ref.ReferenceQueue;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class Finalization {
    public static Finalization finalization;
    @Override
    protected void finalize() {
        System.out.println("Finalized");
        finalization = this;
    }

    public static void main(String[] args) {
        Finalization f =  new Finalization();
        System.out.println("First:"+f);
        f = null;
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Second:"+f);
        System.out.println(f.finalization);
    }
}
