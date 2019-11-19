package com.issac.spring.scope.util;

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
}
