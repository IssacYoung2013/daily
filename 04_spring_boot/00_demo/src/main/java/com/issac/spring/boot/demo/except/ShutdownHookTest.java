package com.issac.spring.boot.demo.except;

/**
 * @author: ywy
 * @date: 2020-03-16
 * @desc:
 */
public class ShutdownHookTest {
    public static void main(String[] args) {
        System.out.println("Hello");
        Thread t = new Thread(()->{
            System.out.println("JVM Close");
        });
        Runtime.getRuntime().addShutdownHook(t);
        System.out.println("World");
    }
}
