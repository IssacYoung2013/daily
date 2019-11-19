package com.issac.spring.di.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
public class BeanDefined {
    private String id;
    private String classPath;
    private String scope = "singleton";
    private String factoryBean = null;
    private String factoryMethod = null;
    private Map<String,Object> propertyMap =new HashMap<>();
}
