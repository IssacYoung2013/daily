package com.issac.bfxy.paya;

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
@ComponentScan(basePackages = {"com.issac.bfxy.paya.*","com.issac.bfxy.paya.config.*"})
@MapperScan(basePackages = {"com.issac.bfxy.paya.mapper"})
public class MainConfig {

}
