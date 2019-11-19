package com.issac.spring.aop.annotation;

import com.issac.spring.aop.annotation.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext_2.xml")
public class TestAspect {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        userService.saveUser();
    }
}
