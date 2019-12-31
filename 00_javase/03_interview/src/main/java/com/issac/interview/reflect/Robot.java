package com.issac.interview.reflect;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class Robot {
    private String name;

    public void sayHi(String helloSentence) {
        System.out.println(helloSentence + " " + name);
    }

    private String throwHello(String tag) {
        return "Hello " + tag;
    }

    static {
        System.out.println("Hello Robot");
    }
}
