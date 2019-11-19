package com.issac.spring.aop.util;

import com.issac.spring.aop.service.BaseService;
import com.issac.spring.aop.service.impl.Person;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
public class MyProxyUtils {

    public static BaseService getProxy(Person person) {
        return (BaseService) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("记录日志-开始");
                        Object result = method.invoke(person, args);
                        System.out.println("记录日志-结束");
                        return result;
                    }
                });
    }

    /**
     * 使用 cglib 动态代理技术
     * 基于继承的方式实现
     * @param service
     * @return
     */
    public static BaseService getProxyCglib(BaseService service) {

        // 创建增强器
        Enhancer enhancer = new Enhancer();

        // 设置需要增强类的类对象
        enhancer.setSuperclass(Person.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 实际调用的是目标对象的方法
                System.out.println("记录日志-开始");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("记录日志-结束");
                return result;
            }
        });

        return (BaseService) enhancer.create();
    }
}
