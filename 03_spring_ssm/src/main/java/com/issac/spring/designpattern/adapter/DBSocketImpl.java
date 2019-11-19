package com.issac.spring.designpattern.adapter;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class DBSocketImpl implements DBSocket {
    @Override
    public void charge() {
        System.out.println("德国标准两孔插排充电");
    }
}
