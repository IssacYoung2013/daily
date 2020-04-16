package com.issac.spring.boot.demo.ioc.xml;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
public class AnimalFactory {

    public static Animal getAnimal(String type) {
        if("dog".equalsIgnoreCase(type)) {
            return new Dog();
        }
        else if("cat".equalsIgnoreCase(type)) {
            return new Cat();
        }
        return null;
    }
}
