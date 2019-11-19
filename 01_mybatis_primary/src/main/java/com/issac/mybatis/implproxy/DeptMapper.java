package com.issac.mybatis.implproxy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class DeptMapper implements SqlSession {

    PreparedStatement ps;

    public int save(String sql) throws SQLException {
        int num = ps.executeUpdate(sql);
        return num;
    }
}
