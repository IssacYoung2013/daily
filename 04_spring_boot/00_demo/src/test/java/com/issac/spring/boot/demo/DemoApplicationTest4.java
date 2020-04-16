package com.issac.spring.boot.demo;

import com.issac.spring.boot.demo.ioc.ann.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@RunWith(SpringRunner.class)
@TestPropertySource({"demo.properties"})
@SpringBootTest(properties = "issac.website.url=issacyoung9.cn")
public class DemoApplicationTest4 {


}
