package com.issac.spring.boot.demo.ioc.xml;

import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Component
public class Bird implements Animal {
    @Override
    public String getName() {
        return "Bird";
    }
}
