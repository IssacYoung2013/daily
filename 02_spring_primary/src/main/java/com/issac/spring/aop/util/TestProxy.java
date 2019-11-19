package com.issac.spring.aop.util;

import com.issac.spring.aop.service.BaseService;
import com.issac.spring.aop.service.impl.Person;
import org.junit.Test;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
public class TestProxy {

    @Test
    public void testJDKProxy() {
        BaseService service = MyProxyUtils.getProxy(new Person());
        service.eat();
    }

    @Test
    public void testCglibProxy() {
        BaseService service = MyProxyUtils.getProxyCglib(new Person());
        service.eat();
    }

}
