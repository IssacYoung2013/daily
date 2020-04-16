package com.issac.interview.jvm.gc;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class ReferenceCounter {
    public static void main(String[] args) {
        MyObject o1 = new MyObject();
        MyObject o2 = new MyObject();
        o1.childNode = o2;
        o2.childNode = o1;
    }
}
