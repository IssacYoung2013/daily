package com.issac.spring.aop.advisor;

import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class MyMethodMatcher implements MethodMatcher {

    /**
     * 被监控接口 无重载方法
     * 此时采用 static 检测方式，只根据方法名称判断
     *
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        String methodName = method.getName();
        if ("eat".equalsIgnoreCase(methodName)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }
}
