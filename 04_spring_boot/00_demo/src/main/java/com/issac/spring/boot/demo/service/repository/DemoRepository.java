package com.issac.spring.boot.demo.service.repository;

import com.issac.spring.boot.demo.bean.Demo;

/**
 * @author: ywy
 * @date: 2020-04-12
 * @desc:
 */
public interface DemoRepository {
    Demo queryDemoById(Integer id);
}
