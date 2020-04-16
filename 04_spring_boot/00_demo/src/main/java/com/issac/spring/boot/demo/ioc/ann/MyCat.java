package com.issac.spring.boot.demo.ioc.ann;

import com.issac.spring.boot.demo.ioc.xml.Animal;
import com.issac.spring.boot.demo.ioc.xml.Cat;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Component
public class MyCat implements FactoryBean<Animal> {
    @Override
    public Animal getObject() throws Exception {
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }
}
