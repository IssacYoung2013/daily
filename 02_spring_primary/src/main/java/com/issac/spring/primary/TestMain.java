package com.issac.spring.primary;

import com.issac.spring.beans.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student);
    }
}
