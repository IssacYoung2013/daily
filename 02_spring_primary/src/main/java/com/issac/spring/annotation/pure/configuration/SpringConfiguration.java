package com.issac.spring.annotation.pure.configuration;

import com.issac.spring.annotation.pure.service.UserService;
import com.issac.spring.annotation.pure.service.impl.UserServiceImpl;
import org.springframework.context.annotation.*;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc: @Configuration 相当于 <beans></beans>
 *          @ComponentScan 相当于 <context:component-scan></context:component-scan>
 */
@Configuration
@ComponentScan(basePackages = "com.issac.spring.annotation.pure")
@Import(DaoConfiguration.class)
public class SpringConfiguration {

    public SpringConfiguration() {
        System.out.println("容器初始化");
    }

//    @Bean("userService")
//    @Scope("prototype")
//    public UserService userService() {
//        return new UserServiceImpl();
//    }
}
