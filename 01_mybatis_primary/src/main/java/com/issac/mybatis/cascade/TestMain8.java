package com.issac.mybatis.cascade;

import com.issac.mybatis.beans.Dept;
import com.issac.mybatis.beans.Employee;
import com.issac.mybatis.mapper.DeptMapper;
import com.issac.mybatis.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-25
 * @desc:
 */
public class TestMain8 {
    static DeptMapper mapper;
    static EmpMapper empMapper;
    static SqlSession session;

    public static void main(String[] args) throws Exception {
        InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        mapper = session.getMapper(DeptMapper.class);
        empMapper = session.getMapper(EmpMapper.class);
        test3();
        session.close();
    }

    static void test1() {
        Dept dept = mapper.findDeptById(20);
        System.out.println(dept);
    }

    static void test2() {
        Dept dept = mapper.findDeptByDeptno(10);
        System.out.println(dept);
    }

    static void test3() {
        Employee employee = empMapper.findEmpById(7782);
        System.out.println(employee);
    }
}
