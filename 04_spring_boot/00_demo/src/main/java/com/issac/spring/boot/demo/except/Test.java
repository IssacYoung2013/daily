package com.issac.spring.boot.demo.except;

/**
 * @author: ywy
 * @date: 2020-03-15
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        try {
            throw new CException(new BException(new AException(new Exception("test"))));
        } catch (Throwable t) {
            while (t != null) {
                System.out.println(t.getClass());
                t = t.getCause();
            }
        }
    }
}
