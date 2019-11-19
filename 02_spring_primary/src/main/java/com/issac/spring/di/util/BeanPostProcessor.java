package com.issac.spring.di.util;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public interface BeanPostProcessor {
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
