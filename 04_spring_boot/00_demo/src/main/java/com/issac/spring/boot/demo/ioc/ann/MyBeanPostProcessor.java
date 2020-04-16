package com.issac.spring.boot.demo.ioc.ann;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-04
 * @desc:
 */
@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanName.equalsIgnoreCase("worker")) {
            return new Worker();
        }
        return null;
    }
}
