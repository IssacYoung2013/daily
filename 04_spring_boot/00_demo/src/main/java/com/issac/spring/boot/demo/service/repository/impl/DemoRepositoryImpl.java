package com.issac.spring.boot.demo.service.repository.impl;

import com.issac.spring.boot.demo.bean.Demo;
import com.issac.spring.boot.demo.mapper.DemoMapper;
import com.issac.spring.boot.demo.service.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-04-12
 * @desc:
 */
@Component
public class DemoRepositoryImpl implements DemoRepository {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public Demo queryDemoById(Integer id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}
