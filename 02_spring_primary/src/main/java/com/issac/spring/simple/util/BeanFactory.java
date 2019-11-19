package com.issac.spring.simple.util;

import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
public class BeanFactory {

    private List<BeanDefined> beanDefinedList;

    public Object getBean(final String beanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Optional<BeanDefined> beanOptional = beanDefinedList.stream()
                .filter(b -> beanId.equals(b.getId()))
                .findFirst();
        if (beanOptional.isPresent()) {
            BeanDefined bean = beanOptional.get();
            String classPath = bean.getClassPath();
            Class classFile = Class.forName(classPath);
            // 默认情况下 Spring工厂通过调用当前类默认构造方法创建实例对象
            Object instance = classFile.newInstance();
            return instance;
        }
        return null;
    }
}
