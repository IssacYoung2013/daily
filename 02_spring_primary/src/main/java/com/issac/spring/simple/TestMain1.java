package com.issac.spring.simple;

import com.issac.spring.simple.util.BeanDefined;
import com.issac.spring.simple.util.BeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain1 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 1. 声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setId("teacher");
        beanObj.setClassPath("com.issac.spring.beans.Teacher");

        // spring核心配置
        List beanList = new ArrayList();
        beanList.add(beanObj);

        // 2. 声明一个Spring提供BeanFactory
        BeanFactory factory = new BeanFactory();
        factory.setBeanDefinedList(beanList);

        // 3. 开发人员索要对象
        Object teacher = factory.getBean("teacher");
        System.out.println(teacher);
    }
}
