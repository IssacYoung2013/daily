package com.issac.spring.boot.mybatis.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ywy
 * @date: 2019-11-03
 * @desc:
 */
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 自动生成key结构：类名_方法名_参数名
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {

        return ((target, method, params) -> {
            // 获取注解所标的方法的类名
            String className = target.getClass().getName();
            String methodName = method.getName();
            return className + "_" + methodName + "_" + params[0].toString();
        });
    }
}
