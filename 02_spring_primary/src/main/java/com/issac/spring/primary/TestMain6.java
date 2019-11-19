package com.issac.spring.primary;

import com.issac.spring.beans.Teacher1;
import com.issac.spring.service.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain6 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config_2.xml");
        Teacher1 teacher = (Teacher1) applicationContext.getBean("teacher");
        System.out.println(teacher);
    }
}
