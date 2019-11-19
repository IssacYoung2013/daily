package com.issac.mybatis.mapper;

import com.issac.mybatis.beans.Employee;

import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public interface EmpMapper {
    List<Employee> findEmp();

    List<Employee> findEmp2(Employee employee);

    Employee findEmpById(Integer empno);
}
