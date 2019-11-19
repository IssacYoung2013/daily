package com.issac.spring.aop.service.impl;

import com.issac.spring.aop.service.BaseService;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class Person implements BaseService {
    @Override
    public void eat() {
        System.out.println("吃饭");
    }

    @Override
    public void wc() {
        System.out.println("上厕所");
    }
}
