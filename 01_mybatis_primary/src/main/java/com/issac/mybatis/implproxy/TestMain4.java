package com.issac.mybatis.implproxy;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class TestMain4 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, SQLException {
        SqlSession dao = SqlSessionFactory.build(DeptMapper.class);
        String sql = "insert into dept values(51,'TEST','BeiJing',1)";
        Map statementMapper = new HashMap();
        statementMapper.put("dept.save",sql);
        dao.save(sql);
    }
}
