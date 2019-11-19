package com.issac.spring.annotation.pure;

import com.issac.spring.annotation.pure.configuration.SpringConfiguration;
import com.issac.spring.annotation.pure.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class Test2 {
    ApplicationContext context;

    @Before
    public void init() {
        // 创建纯注解方式spring容器
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }

    @Test
    public void test() {
        // 从容器中获取bean实例
        UserService userService = context.getBean(UserService.class);
        userService.saveUser();
    }
}
