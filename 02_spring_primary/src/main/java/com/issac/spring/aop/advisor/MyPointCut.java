package com.issac.spring.aop.advisor;

import lombok.Data;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
public class MyPointCut implements Pointcut {

    /**
     * InvocationHandler 接口
     * invoke() {
     *     if(obj.getClass() != Person.class) {
     *         return
     *     }
     *
     *     if("eat".equal(methodObj.getName())){
     *         return;
     *     }
     *
     *     wash();
     *     Person.eat();
     * }
     */
    private ClassFilter classFilter;

    private MethodMatcher methodMatcher;

    @Override
    public ClassFilter getClassFilter() {
        return classFilter;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }
}
