package com.issac.spring.dynamicfactory.util;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
public class BeanFactory {

    private List<BeanDefined> beanDefinedList;
    private Map<String, Object> springIoc;

    public BeanFactory(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
        springIoc = new HashMap<>();
        this.beanDefinedList.stream().forEach(bean -> {
            if ("singleton".equals(bean.getScope())) {
                try {
                    Class classFile = Class.forName(bean.getClassPath());
                    Object instance = classFile.newInstance();
                    springIoc.put(bean.getId(), instance);
                } catch (Exception e) {
                }
            }
        });
    }

    public Object getBean(final String beanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Optional<BeanDefined> beanOptional = beanDefinedList.stream()
                .filter(b -> beanId.equals(b.getId()))
                .findFirst();
        if (beanOptional.isPresent()) {
            BeanDefined bean = beanOptional.get();
            String classPath = bean.getClassPath();
            String scope = bean.getScope();
            String factoryBean = bean.getFactoryBean();
            String factoryMethod = bean.getFactoryMethod();
            Object instance = null;
            if ("prototype".equals(scope)) {
                // 使用指定工厂创建实例对象
                if (factoryBean != null && factoryMethod != null) {
                    Object factoryObj = springIoc.get(factoryBean);
                    Class<?> factoryClass = factoryObj.getClass();
                    Method methodObj =
                            factoryClass.getDeclaredMethod(factoryMethod,null);
                    methodObj.setAccessible(true);
                    instance = methodObj.invoke(factoryObj, null);
                }
                Class classFile = Class.forName(classPath);
                instance = classFile.newInstance();
            } else {
                instance = springIoc.get(beanId);
            }
            return instance;
        }
        return null;
    }
}
