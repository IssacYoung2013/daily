package com.issac.mybatis.proxy;

import java.lang.reflect.Proxy;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class ProxyFactory {

    /**
     * JDK动态代理模式，代理对象的数据类型，应该由监控行为决定
     * @param clazz 被监控的类
     */
    public static BaseService build(Class clazz) {
        try {
            BaseService obj = (BaseService) clazz.newInstance();
            return (BaseService) Proxy.newProxyInstance(clazz.getClassLoader()
                    ,clazz.getInterfaces(),new Invocation(obj));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
