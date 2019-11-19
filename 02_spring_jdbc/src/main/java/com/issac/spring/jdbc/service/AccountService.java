package com.issac.spring.jdbc.service;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
public interface AccountService {
    void transfer(String from,String to,Double money);
}
