package com.issac.spring.jdbc.service.impl;

import com.issac.spring.jdbc.dao.AccountDao;
import com.issac.spring.jdbc.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@Service
// 标记该类所有方法已经被事务管理
@Transactional
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountDao dao;

    @Override
    public void transfer(String from, String to, Double money) {
        double fromMoney = dao.queryMoney(from);
        // 对from账户扣钱操作
        dao.update(from, fromMoney - money);
        int i = 1/0;
        // 对to账户加钱操作
        double toMoney = dao.queryMoney(to);
        dao.update(to, toMoney + money);
    }
}
