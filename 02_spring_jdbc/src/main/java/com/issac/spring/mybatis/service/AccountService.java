package com.issac.spring.mybatis.service;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-28
 * @desc:
 */
public interface AccountService {
    void transfer(String from,String to,Double money);
}
