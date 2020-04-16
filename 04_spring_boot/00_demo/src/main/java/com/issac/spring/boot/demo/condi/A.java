package com.issac.spring.boot.demo.condi;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-04-05
 * @desc:
 */
@Component
//@ConditionalOnProperty("com.issac.condition")
@ConditionalOnProperty({"com.issac.condition1","com.issac.condition2"})
public class A {

}
