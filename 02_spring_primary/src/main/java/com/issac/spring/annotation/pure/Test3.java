package com.issac.spring.annotation.pure;

import com.issac.spring.annotation.pure.configuration.SpringConfiguration;
import com.issac.spring.annotation.pure.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class} )
public class Test3 {

    @Resource
    UserService userService;

    @Test
    public void test() {
        // 从容器中获取bean实例
        userService.saveUser();
    }
}
