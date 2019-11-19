package com.issac.spring.boot.servlet2.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-03
 * @desc:
 */
@Configuration
public class MyApplicationContext {

    @Bean
    public ServletRegistrationBean<SomeServlet> getServletBean() {
        return new ServletRegistrationBean<>(new SomeServlet(),"/some");
    }
}
