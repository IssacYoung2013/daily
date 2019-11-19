package com.issac.bfxy.payb;

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
@ComponentScan(basePackages = {"com.issac.bfxy.payb.*"})
@MapperScan(basePackages = {"com.issac.bfxy.payb.mapper"})
public class MainConfig {

}
