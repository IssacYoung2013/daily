package com.issac.spring.designpattern.decorate;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class iPhone6 implements Phone {
    @Override
    public void call() {
        System.out.println("Call By iPhone6");
    }
}
