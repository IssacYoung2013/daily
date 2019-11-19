package com.issac.spring.aop.advisor;

import com.issac.spring.aop.service.impl.Person;
import org.springframework.aop.ClassFilter;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class MyClassFilter implements ClassFilter {
    @Override
    public boolean matches(Class<?> clazz) {
        if (clazz == Person.class) {
            return true;
        }
        return false;
    }
}
