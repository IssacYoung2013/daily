package com.issac.mybatis.proxy;


/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class TestMain3 {
    public static void main(String[] args) {

        BaseService instance = ProxyFactory.build(Person.class);
        instance.eat();
        instance.wc();
    }
}
