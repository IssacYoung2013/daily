package com.issac.spring.aop.service.impl;

import com.issac.spring.aop.service.BaseService;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class Dog implements BaseService {
    @Override
    public void eat() {
        System.out.println("啃骨头");
    }

    @Override
    public void wc() {
        System.out.println("嘘嘘");
    }
}
