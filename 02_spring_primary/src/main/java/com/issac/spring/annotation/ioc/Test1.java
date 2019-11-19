package com.issac.spring.annotation.ioc;

import com.issac.spring.annotation.ioc.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class Test1 {

    @Test
    public void test1() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service1 = (UserService) applicationContext.getBean("userService");
        UserService service2 = (UserService) applicationContext.getBean(UserService.class);

        service1.saveUser();
        service2.saveUser();

    }
}
