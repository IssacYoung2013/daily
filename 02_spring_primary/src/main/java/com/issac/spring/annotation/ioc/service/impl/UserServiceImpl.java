package com.issac.spring.annotation.ioc.service.impl;

import com.issac.spring.annotation.ioc.dao.UserDao;
import com.issac.spring.annotation.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserDao userDao;

//    @Qualifier("date")
//    Date date;

//    @Resource
//    Date date1;

    @Value("${id}")
    int id;

    @Override
    public void saveUser() {
        System.out.println("IoC演示 UserService " + id);
    }
}
