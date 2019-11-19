package com.issac.mybatis.operator;


import com.issac.mybatis.beans.Dept;
import com.issac.mybatis.mapper.DeptMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-10-20
 * @desc:
 */
public class TestMain5 {
    static DeptMapper mapper;

    public static void main(String[] args) throws Exception {

        InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();
        mapper = session.getMapper(DeptMapper.class);
        test2();
        session.close();
    }

    static void test1() {
        List<Dept> deptList = mapper.findByDeptName1("金融事业部");
        System.out.println(deptList);
    }

    static void test2() {
        List<Dept> deptList = mapper.findDept1("dept1");
        System.out.println(deptList);
    }
}
