package com.issac.spring.primary;

import com.issac.spring.service.impl.BaseServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean对象初始化之前");
        // 返回bean对象或者代理对象
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object beanInstance, String beanName) throws BeansException {
        // 为当前bean对象注册代理监控对象，负责增强bean对象方法能力
        Class<?> beanClass = beanInstance.getClass();
        if (beanClass == BaseServiceImpl.class) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("doSome被拦截");
                            String methodName = method.getName();
                            String result = (String) method.invoke(beanInstance, null);
                            if ("doSome".equals(methodName)) {
                                result = result.toUpperCase();
                            }
                            return result;
                        }
                    });
        }
        System.out.println("bean对象初始化之后");
        return beanInstance;
    }
}
