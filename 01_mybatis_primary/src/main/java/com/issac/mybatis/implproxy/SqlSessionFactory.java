package com.issac.mybatis.implproxy;

import java.lang.reflect.Proxy;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class SqlSessionFactory {
    public static SqlSession build(Class clazz) throws IllegalAccessException, InstantiationException {
        SqlSession instance = (SqlSession) clazz.newInstance();
        return (SqlSession) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),new Invocation(instance));
    }
}
