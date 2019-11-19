package com.issac.spring.dynamicfactory.util;

import lombok.Data;

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
}
