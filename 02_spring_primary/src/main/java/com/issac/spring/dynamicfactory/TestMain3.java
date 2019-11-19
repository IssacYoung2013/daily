package com.issac.spring.dynamicfactory;

import com.issac.spring.beans.Teacher;
import com.issac.spring.dynamicfactory.util.BeanDefined;
import com.issac.spring.dynamicfactory.util.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain3 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 1. 声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setId("teacher");
        beanObj.setClassPath("com.issac.spring.beans.Teacher");
        beanObj.setFactoryBean("factory1");
        beanObj.setFactoryMethod("createTeacher");
        beanObj.setScope("prototype");

        BeanDefined beanObj2 = new BeanDefined();
        beanObj2.setId("factory1");
        beanObj2.setClassPath("com.issac.spring.primary.TeacherFactory");

        // spring核心配置
        List beanList = new ArrayList();
        beanList.add(beanObj);
        beanList.add(beanObj2);

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
