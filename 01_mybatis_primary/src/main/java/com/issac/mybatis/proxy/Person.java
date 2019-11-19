package com.issac.mybatis.proxy;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class Person implements BaseService {

    public void eat() {
        System.out.println("吃饭");
    }

    public void wc() {
        System.out.println("上厕所");
    }
}
