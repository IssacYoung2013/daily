package com.issac.spring.boot.demo.ioc.xml;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Data
@Component
public class HelloService {
    private Student student;

    @Autowired
    @Qualifier("bird")
    private Animal animal;

    public String hello() {
        return "hello";
//        return student.toString();
    }

    public String getName() {
       return animal.getName();
    }
}
