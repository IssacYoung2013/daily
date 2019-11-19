package com.issac.mybatis;


import com.issac.mybatis.beans.Dept;
import com.issac.mybatis.mapper.DeptMapper;
import org.apache.ibatis.executor.statement.StatementHandler;
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
public class TestMain2 {
    public static void main(String[] args) throws Exception {

        Dept dept = new Dept();
        dept.setDname("金融事业部");
        dept.setLoc("北京");

        // Mybatis 全局配置文件
        InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();
//        session.insert("insertDept",dept);
//        session.commit();
        DeptMapper mapper = session.getMapper(DeptMapper.class);
        List<Dept> deptList = mapper.findDept();
        System.out.println(deptList);
        session.close();
    }
}
