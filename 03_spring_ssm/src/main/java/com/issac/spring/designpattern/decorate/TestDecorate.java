package com.issac.spring.designpattern.decorate;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class TestDecorate {
    public static void main(String[] args) {
        Phone phone = new iPhoneDecorate(new iPhone6());
        phone.call();
    }
}
