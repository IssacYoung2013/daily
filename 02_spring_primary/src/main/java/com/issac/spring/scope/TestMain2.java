package com.issac.spring.scope;

import com.issac.spring.beans.Teacher;
import com.issac.spring.scope.util.BeanDefined;
import com.issac.spring.scope.util.BeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain2 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 1. 声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setId("teacher");
        beanObj.setClassPath("com.issac.spring.beans.Teacher");
//        beanObj.setScope("prototype");

        // spring核心配置
        List beanList = new ArrayList();
        beanList.add(beanObj);

        // 2. 声明一个Spring提供BeanFactory
        BeanFactory factory = new BeanFactory(beanList);
        factory.setBeanDefinedList(beanList);

        // 3. 开发人员索要对象
        Teacher teacher = (Teacher) factory.getBean("teacher");
        System.out.println(teacher);
        teacher = (Teacher) factory.getBean("teacher");
        System.out.println(teacher);
    }
}
