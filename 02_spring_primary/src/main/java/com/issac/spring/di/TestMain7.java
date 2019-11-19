package com.issac.spring.di;

import com.issac.spring.beans.Teacher1;
import com.issac.spring.di.util.BeanDefined;
import com.issac.spring.di.util.BeanFactory;
import com.issac.spring.service.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain7 {
    public static void main(String[] args) throws Exception {
        // 1. 声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setId("teacher");
        beanObj.setClassPath("com.issac.spring.beans.Teacher1");
        /**
         * property
         */
        Map propertyMap = beanObj.getPropertyMap();
        propertyMap.put("teacherName", "Mr Zhao");
        propertyMap.put("friendArray", "mike,tom,allen");
        propertyMap.put("school", "北京航空大学,江西师范大学");

        List beanList = new ArrayList();
        beanList.add(beanObj);

        // 2. 声明一个Spring提供BeanFactory
        BeanFactory factory = new BeanFactory(beanList);
        factory.setBeanDefinedList(beanList);

        // 3. 开发人员索要对象
        Teacher1 teacher1 = (Teacher1) factory.getBean("teacher");
        System.out.println(teacher1);
    }
}
