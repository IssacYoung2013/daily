package com.issac.spring.aop.advisor;

import lombok.Data;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
public class MyPointCutAdvisor implements PointcutAdvisor {

    /**
     * 次要业务与主要业务的执行顺序
     */
    private Advice advice;

    /**
     * 当前拦截对象的主要业务方法
     */
    private Pointcut pointcut;

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
