package com.issac.spring.mybatis.service.impl;

import com.issac.spring.mybatis.mapper.AccountMapper;
import com.issac.spring.mybatis.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ywy
 * @date: 2019-10-28
 * @desc:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper mapper;

    @Override
    public void transfer(String from, String to, Double money) {
        double fromMoney = mapper.queryMoney(from);
        mapper.update(from, fromMoney - money);
        double toMoney = mapper.queryMoney(to);
        mapper.update(to, toMoney + money);
    }
}
