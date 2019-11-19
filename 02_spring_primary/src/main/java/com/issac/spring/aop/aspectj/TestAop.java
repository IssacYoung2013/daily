package com.issac.spring.aop.aspectj;

import com.issac.spring.aop.aspectj.service.UserService;
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
@ContextConfiguration(locations = {"classpath:applicationContext_1.xml"})
public class TestAop {

    @Autowired
    UserService userService;

    @Test
    public void testBefore() {
        userService.saveUser();
        userService.saveUser("李四");
    }
}
