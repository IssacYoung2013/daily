package com.issac.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class Invocation implements InvocationHandler {

    private BaseService obj;

    public Invocation(BaseService obj) {
        this.obj = obj;
    }

    /**
     * 被监控的行为将要执行时，会被JVM拦截
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if("eat".equals(methodName)) {
            System.out.println("洗手");
        }
        Object result = method.invoke(obj, args);
        if("wc".equals(methodName)) {
            System.out.println("洗手");
        }
        return result;
    }
}
