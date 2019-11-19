package com.issac.spring.annotation.pure.service.impl;

import com.issac.spring.annotation.pure.service.UserService;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${id}")
    private int id;

    private String name;

    @Override
    public void saveUser() {
        System.out.println("IoC演示 UserService " + id);
    }
}
