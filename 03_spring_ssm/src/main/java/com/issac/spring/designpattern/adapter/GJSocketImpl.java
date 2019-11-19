package com.issac.spring.designpattern.adapter;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class GJSocketImpl implements GJSocket {
    @Override
    public void charge() {
        System.out.println("国际标准插排充电");
    }
}
