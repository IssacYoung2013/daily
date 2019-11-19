package com.issac.spring.postprocessor;

import com.issac.spring.beans.Teacher;
import com.issac.spring.postprocessor.util.*;
import com.issac.spring.service.BaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain5 {
    public static void main(String[] args) throws Exception {
        // 1. 声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setId("baseService");
        beanObj.setClassPath("com.issac.spring.service.impl.BaseServiceImpl");

        BeanDefined beanObj2 = new BeanDefined();
        beanObj2.setClassPath("com.issac.spring.postprocessor.util.MyBeanPostProcessor");

        // spring核心配置
        List beanList = new ArrayList();
        beanList.add(beanObj);
        beanList.add(beanObj2);

        // 2. 声明一个Spring提供BeanFactory
        BeanFactory factory = new BeanFactory(beanList);
        factory.setBeanDefinedList(beanList);

        // 3. 开发人员索要对象
        BaseService baseService = (BaseService) factory.getBean("baseService");
        System.out.println(baseService.doSome());
    }
}
