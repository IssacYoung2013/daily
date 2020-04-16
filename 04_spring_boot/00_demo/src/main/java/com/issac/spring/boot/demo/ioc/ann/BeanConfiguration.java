package com.issac.spring.boot.demo.ioc.ann;

import com.issac.spring.boot.demo.ioc.xml.Animal;
import com.issac.spring.boot.demo.ioc.xml.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Configuration
public class BeanConfiguration {
    @Bean("dog")
    public Animal getDog() {
        return new Dog();
    }
}
