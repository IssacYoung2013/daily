package com.issac.spring.aop.annotation;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc: @Aspect 切面类
 */
@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* *..*.*ServiceImpl.*(..))")
    public void pointCut() {

    }

    @Before(value = "pointCut()")
    public void before() {
        System.out.println("注解前置通知");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning() {
        System.out.println("注解后置通知");
    }

    @After(value = "pointCut()")
    public void after() {
        System.out.println("注解最终通知");
    }

    @AfterThrowing(value = "pointCut()")
    public void afterThrowing() {
        System.out.println("注解异常通知");
    }
}
