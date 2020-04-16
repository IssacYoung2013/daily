package com.issac.interview.thread;

/**
 * @author: ywy
 * @date: 2020-01-03
 * @desc:
 */
public class StringBufferWithoutSync {
    public void add(String s1, String s2) {
        // sb属于不可能共享的资源，JVM会自动消除内部的锁
        StringBuffer sb = new StringBuffer();
        sb.append(s1).append(s2);
    }

    public static void main(String[] args) {
        StringBufferWithoutSync withoutSync = new StringBufferWithoutSync();
        for (int i = 0; i < 100; i++) {
            withoutSync.add("aaa","bbb");
        }
    }

}
