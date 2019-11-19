package com.issac.spring.postprocessor.util;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
public class BeanFactory {

    private List<BeanDefined> beanDefinedList;
    private Map<String, Object> springIoc;
    // 后置对象
    private BeanPostProcessor processorObj;

    public BeanFactory(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
        springIoc = new HashMap<>();
        this.beanDefinedList.stream().forEach(bean -> {
            if ("singleton".equals(bean.getScope())) {
                try {
                    Class classFile = Class.forName(bean.getClassPath());
                    Object instance = classFile.newInstance();
                    // 判断当前对象是一个bean对象还是后置处理对象
                    isProcessor(instance, classFile);
                    springIoc.put(bean.getId(), instance);
                } catch (Exception e) {
                }
            }
        });
    }

    private void isProcessor(Object instance, Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        if (interfaces == null) {
            return;
        }
        if (Stream.of(interfaces).anyMatch(it -> it == BeanPostProcessor.class)) {
            processorObj = (BeanPostProcessor) instance;
        }
    }

    public Object getBean(final String beanId) throws Exception {
        Optional<BeanDefined> beanOptional = beanDefinedList.stream()
                .filter(b -> beanId.equals(b.getId()))
                .findFirst();
        if (beanOptional.isPresent()) {
            BeanDefined bean = beanOptional.get();
            String classPath = bean.getClassPath();
            String scope = bean.getScope();
            String factoryBean = bean.getFactoryBean();
            String factoryMethod = bean.getFactoryMethod();
            Object proxyObj = null;
            Object instance = null;
            if ("prototype".equals(scope)) {
                if (factoryBean != null && factoryMethod != null) {
                    Object factoryObj = springIoc.get(factoryBean);
                    Class<?> factoryClass = factoryObj.getClass();
                    Method methodObj =
                            factoryClass.getDeclaredMethod(factoryMethod, null);
                    methodObj.setAccessible(true);
                    instance = methodObj.invoke(factoryObj, null);
                }
                Class classFile = Class.forName(classPath);
                instance = classFile.newInstance();
            } else {
                instance = springIoc.get(beanId);
            }
            if (this.processorObj != null) {
                proxyObj = this.processorObj.postProcessBeforeInitialization(instance, beanId);
                // 实例对象初始化 Spring 依赖注入
                proxyObj = this.processorObj.postProcessAfterInitialization(instance, beanId);
                return proxyObj;
            }
            return instance;
        }
        return null;
    }
}
