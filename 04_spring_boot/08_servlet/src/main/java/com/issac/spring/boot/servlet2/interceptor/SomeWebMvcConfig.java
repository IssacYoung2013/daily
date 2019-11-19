package com.issac.spring.boot.servlet2.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: ywy
 * @date: 2019-11-03
 * @desc:
 */
@Configuration
public class SomeWebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        SomeInterceptor someInterceptor = new SomeInterceptor();
        registry.addInterceptor(someInterceptor)
                .addPathPatterns("/first/**")
                .excludePathPatterns("/second/**")
                .excludePathPatterns("/first/aaa");

        super.addInterceptors(registry);
    }
}
