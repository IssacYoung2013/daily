package com.issac.mybatis.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-21
 * @desc:
 */
public class SqlSessionFactory {
    public static SqlSession build(Class clazz) throws IllegalAccessException, InstantiationException {
        // 1. 创建本次被监控的实例对象
        SqlSession dao = (SqlSession) clazz.newInstance();
        // 2. 创建本次负责监控的对象
        InvocationHandler agent = new Agent(dao);
        // 3. 向JVM发送监控申请
        SqlSession daoFalse = (SqlSession) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),agent);
        return daoFalse;
    }
}
