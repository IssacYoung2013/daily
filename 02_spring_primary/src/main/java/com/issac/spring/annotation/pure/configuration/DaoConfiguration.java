package com.issac.spring.annotation.pure.configuration;

import com.issac.spring.annotation.pure.service.UserService;
import com.issac.spring.annotation.pure.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@Configuration
public class DaoConfiguration {
    public DaoConfiguration() {
        System.out.println("DaoConfiguration 被加载...");
    }
}
