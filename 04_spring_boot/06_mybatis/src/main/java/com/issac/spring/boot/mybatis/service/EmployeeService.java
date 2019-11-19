package com.issac.spring.boot.mybatis.service;

import com.issac.spring.boot.mybatis.po.EmployeePo;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-02
 * @desc:
 */
public interface EmployeeService {
    void addEmployee(EmployeePo employeePo);

    EmployeePo findEmployeeById(Integer id);

    Integer findEmployeeCount();
}
