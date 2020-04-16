package com.issac.spring.boot.demo.service.impl;

import com.issac.spring.boot.demo.bean.Demo;
import com.issac.spring.boot.demo.service.DemoQueryService;
import com.issac.spring.boot.demo.service.repository.DemoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: ywy
 * @date: 2020-04-12
 * @desc:
 */
@Component
public class DemoQueryServiceImpl implements DemoQueryService {

    @Resource(name = "demoRepositoryCacheImpl")
    private DemoRepository demoRepository;

    @Override
    public Demo queryDemoById(Integer id) {
        return demoRepository.queryDemoById(id);
    }
}
