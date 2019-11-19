package com.issac.spring.jdbc.dao;

/**
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
public interface AccountDao {
    void update(String name, Double money);
    double queryMoney(String name);
}
