package com.issac.spring.aop.annotation.service.impl;

import com.issac.spring.aop.annotation.service.UserService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@Component("userService")
@PropertySource("classpath:data.properties")
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser() {
        System.out.println("添加用户 ");
//        int i = 1/0;
    }

    @Override
    public void saveUser(String name) {
        System.out.println("添加用户 " + name);
    }
}
