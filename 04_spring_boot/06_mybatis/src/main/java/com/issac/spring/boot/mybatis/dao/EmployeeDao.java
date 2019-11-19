package com.issac.spring.boot.mybatis.dao;

import com.issac.spring.boot.mybatis.po.EmployeePo;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-02
 * @desc:
 */
@Mapper
public interface EmployeeDao {

    void insertEmployee(EmployeePo employee);

    Integer selectEmployeeCount();

    EmployeePo selectEmployeeById(Integer id);
}
