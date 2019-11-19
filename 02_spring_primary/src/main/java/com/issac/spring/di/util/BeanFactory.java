package com.issac.spring.di.util;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
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

    /**
     * 依赖注入
     *
     * @param instance
     * @param clazz
     * @param propertyMap
     */
    public void setValue(Object instance, Class clazz, Map<String, Object> propertyMap) {
        Method[] methods = clazz.getDeclaredMethods();
        Set<Map.Entry<String, Object>> fieldNameSet = propertyMap.entrySet();
        fieldNameSet.stream().forEach(fieldName -> {
            try {
                Field field = clazz.getDeclaredField(fieldName.getKey());
                String value = (String) fieldName.getValue();
                Arrays.stream(methods).forEach(method -> {
                    String methodName = "set" + fieldName.getKey();
                    if (methodName.equalsIgnoreCase(method.getName())) {
                        Class<?> fieldType = field.getType();
                        if (fieldType == String.class) {
                            try {
                                method.invoke(instance, value);
                            } catch (Exception e) {
                            }
                        } else if (fieldType == Integer.class) {
                            try {
                                method.invoke(instance, Integer.valueOf(value));
                            } catch (Exception e) {
                            }
                        } else if (fieldType == Boolean.class) {
                            try {
                                method.invoke(instance, Boolean.valueOf(value));
                            } catch (Exception e) {
                            }
                        } else if (fieldType == List.class) {
                            try {
                                List tempList = Arrays.asList(value.split(","));
                                method.invoke(instance, tempList);
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                String[] valueArray = value.split(",");
                                Object data[] = new Object[1];
                                data[0] = valueArray;
                                method.invoke(instance, data);
                            } catch (Exception e) {
                            }
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

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
            Class classFile = Class.forName(classPath);

            if ("prototype".equals(scope)) {
                if (factoryBean != null && factoryMethod != null) {
                    Object factoryObj = springIoc.get(factoryBean);
                    Class<?> factoryClass = factoryObj.getClass();
                    Method methodObj =
                            factoryClass.getDeclaredMethod(factoryMethod, null);
                    methodObj.setAccessible(true);
                    instance = methodObj.invoke(factoryObj, null);
                }
                instance = classFile.newInstance();
            } else {
                instance = springIoc.get(beanId);
            }
            if (this.processorObj != null) {
                proxyObj = this.processorObj.postProcessBeforeInitialization(instance, beanId);
                // 实例对象初始化 Spring 依赖注入
                setValue(instance, classFile, bean.getPropertyMap());
                proxyObj = this.processorObj.postProcessAfterInitialization(instance, beanId);
                return proxyObj;
            } else {
                setValue(instance, classFile, bean.getPropertyMap());
                return instance;
            }
        }
        return null;
    }
}
