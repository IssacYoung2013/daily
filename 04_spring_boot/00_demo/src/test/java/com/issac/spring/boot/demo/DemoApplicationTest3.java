package com.issac.spring.boot.demo;

import com.issac.spring.boot.demo.ioc.ann.MyBeanImport;
import com.issac.spring.boot.demo.ioc.ann.Teacher;
import com.issac.spring.boot.demo.ioc.xml.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest3 {

    @Autowired
    Teacher teacher;

    @Test
    public void testName() {
        System.out.println(teacher.getName());
    }
}
