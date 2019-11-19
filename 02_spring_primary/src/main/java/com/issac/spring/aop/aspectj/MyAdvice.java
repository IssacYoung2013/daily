package com.issac.spring.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
public class MyAdvice {
    public void log() {
        System.out.println("记录日志");
    }

    public void log2() {
        System.out.println("记录日志2");
    }

    public void log3() {
        System.out.println("记录日志3");
    }

    public void log4() {
        System.out.println("记录日志4");
    }

    public Object log5(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕通知前");
        Object result = null;
        try {
            result = joinPoint.proceed();

            System.out.println("环绕通知后");
            return result;
        } catch (Throwable throwable) {
            System.out.println("出异常");
        } finally {
            System.out.println("最终通知");
        }
        return null;
    }


}
