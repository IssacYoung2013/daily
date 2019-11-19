package com.issac.bfxy.order;

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
@ComponentScan(basePackages = {"com.issac.bfxy.order.*","com.issac.bfxy.order.config.*"})
@MapperScan(basePackages = {"com.issac.bfxy.order.mapper"})
public class MainConfig {

}
