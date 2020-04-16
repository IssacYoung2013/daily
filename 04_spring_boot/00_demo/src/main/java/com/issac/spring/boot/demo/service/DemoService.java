package com.issac.spring.boot.demo.service;

import com.issac.spring.boot.demo.bean.Demo;
import com.issac.spring.boot.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Service
public class DemoService {

    @Resource
    DemoMapper demoMapper;

    public Demo getDemoById(int id) {
        return Optional.ofNullable(demoMapper.selectByPrimaryKey(id))
                .orElse(null);
    }
}
