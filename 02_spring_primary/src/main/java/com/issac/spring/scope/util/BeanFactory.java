package com.issac.spring.scope.util;

import lombok.Data;

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

    public BeanFactory(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
        springIoc = new HashMap<>();
        this.beanDefinedList.stream().forEach(bean -> {
            if ("singleton".equals(bean.getScope())) {
                try {
                    Class classFile = Class.forName(bean.getClassPath());
                    Object instance = classFile.newInstance();
                    springIoc.put(bean.getId(),instance);
                } catch (Exception e) {
                }
            }
        });
    }

    private Map<String, Object> springIoc;

    public Object getBean(final String beanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Optional<BeanDefined> beanOptional = beanDefinedList.stream()
                .filter(b -> beanId.equals(b.getId()))
                .findFirst();
        if (beanOptional.isPresent()) {
            BeanDefined bean = beanOptional.get();
            String classPath = bean.getClassPath();
            String scope = bean.getScope();
            Object instance = null;
            if ("prototype".equals(scope)) {
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
