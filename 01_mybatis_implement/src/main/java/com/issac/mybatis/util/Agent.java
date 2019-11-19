package com.issac.mybatis.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-21
 * @desc:
 */
public class Agent implements InvocationHandler{
    private SqlSession dao;

    public Agent(SqlSession dao) {
        this.dao = dao;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
