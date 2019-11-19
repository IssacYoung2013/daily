package com.issac.spring.aop.advisor;

import com.issac.spring.aop.service.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TestMain9 {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config_4.xml");
        BaseService personProxy = (BaseService) applicationContext.getBean("personProxy");
        personProxy.eat();
        personProxy.wc();

        BaseService dogProxy = (BaseService) applicationContext.getBean("dogProxy");
        dogProxy.eat();
        dogProxy.wc();
    }
}
