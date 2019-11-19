package com.issac.spring.mybatis.service;

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
@ContextConfiguration(locations = {"classpath:spring/application*.xml"})
public class AccountServiceTest {

    @Autowired
    AccountService service;

    @Test
    public void test() {
        service.transfer("测试1","测试",20.0);
    }
}