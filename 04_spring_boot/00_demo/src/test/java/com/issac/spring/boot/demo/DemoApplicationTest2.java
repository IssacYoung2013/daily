package com.issac.spring.boot.demo;

import com.issac.spring.boot.demo.event.WeatherRunListener;
import com.issac.spring.boot.demo.ioc.ann.MyBeanImport;
import com.issac.spring.boot.demo.ioc.xml.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = "classpath:ioc/demo.xml")
@Import(MyBeanImport.class)
public class DemoApplicationTest2 {

    @Autowired
    HelloService helloService;

    @Test
    public void hello() {
        System.out.println(helloService.hello());
        System.out.println(helloService.getName());
    }
}
