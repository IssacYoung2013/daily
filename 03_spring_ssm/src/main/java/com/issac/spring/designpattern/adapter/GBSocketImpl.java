package com.issac.spring.designpattern.adapter;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class GBSocketImpl implements GBSocket {
    @Override
    public void charge() {
        System.out.println("国内标准三孔插排充电");
    }
}
