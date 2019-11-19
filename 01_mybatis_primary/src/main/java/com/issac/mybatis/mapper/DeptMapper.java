package com.issac.mybatis.mapper;

import com.issac.mybatis.beans.Dept;
import com.issac.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: ywy
 * @date: 2019-10-21
 * @desc:
 */
public interface DeptMapper {

    int insertDept(Dept dept);

    List<Dept> findDept();

    List<Dept> findByDeptName1(String dname);

    List<Dept> findByDeptName2(String dname);

    List<Dept> findDept1(String tablename);

    List<Dept> findDept2(Dept dept);

    List<Dept> findDept3(Dept dept);

    int updateDept(Dept dept);

    List<Dept> findDept4(Dept dept);

    int saveDept(List<Dept> list);

    List<Dept> findDeptByList(List deptnoList);

    List<Dept> findDeptByArray(int[] deptnoArray);

    List<Dept> findDeptByMap(@Param("myMap") Map myMap);

    Dept findDeptById(Integer deptno);

    Dept findDeptByDeptno(Integer deptno);
}
