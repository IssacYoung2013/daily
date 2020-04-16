package com.issac.spring.boot.demo.condi;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author: ywy
 * @date: 2020-04-06
 * @desc:
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MyCondition.class)
public @interface MyConditionAnnotation {
    String[] values() default {};
}
