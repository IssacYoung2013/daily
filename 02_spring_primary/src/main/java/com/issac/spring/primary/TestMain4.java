package com.issac.spring.primary;

import com.issac.spring.service.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain4 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config_1.xml");
        BaseService service = (BaseService) applicationContext.getBean("baseService");
        String some = service.doSome();
        System.out.println(some);
    }
}
