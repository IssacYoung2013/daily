package com.issac.mybatis;

import com.issac.mybatis.bean.Dept;
import com.issac.mybatis.util.DefaultSqlSession;
import com.issac.mybatis.util.SqlSession;
import com.issac.mybatis.util.SqlSessionFactory;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-21
 * @desc:
 */
public class TestMain {
    public static void main(String[] args)throws Exception {
        Dept dept = new Dept();
        dept.setDeptno(500);
        dept.setDetpname("社保事业部");
        dept.setLoc("北京");

        SqlSession sqlSession = SqlSessionFactory.build(DefaultSqlSession.class);

        sqlSession.save(dept);
    }
}
