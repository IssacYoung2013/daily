package com.issac.mybatis.resultmap;

import com.issac.mybatis.beans.Dept;
import com.issac.mybatis.beans.Employee;
import com.issac.mybatis.mapper.DeptMapper;
import com.issac.mybatis.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class TestMain6 {
    static EmpMapper mapper;

    public static void main(String[] args) throws Exception {

        InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();
        mapper = session.getMapper(EmpMapper.class);
        test1();
        session.close();
    }

    static void test1() {
        List<Employee> employees = mapper.findEmp();
        System.out.println(employees);
    }
}
