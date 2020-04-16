package com.issac.spring.boot.demo.ioc.ann;

import org.springframework.context.annotation.Bean;

/**
 * @author: ywy
 * @date: 2020-03-28
 * @desc:
 */
public interface SuperConfiguration {
    @Bean
    default MyCat mycat() {
        return new MyCat();
    }
}
