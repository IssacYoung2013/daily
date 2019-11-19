package com.issac.bfxy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author: ywy
 * @date: 2019-11-15
 * @desc:
 */
@Configuration
@ComponentScan(basePackages = {"com.issac.bfxy.store.*"})
@MapperScan(basePackages = {"com.issac.bfxy.store.mapper"})
public class MainConfig {

}
