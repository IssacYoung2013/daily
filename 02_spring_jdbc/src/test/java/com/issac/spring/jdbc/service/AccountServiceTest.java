package com.issac.spring.jdbc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-28
 * @desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring_2.xml")
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void test() {
        accountService.transfer("测试","测试1",10.0);
    }

}