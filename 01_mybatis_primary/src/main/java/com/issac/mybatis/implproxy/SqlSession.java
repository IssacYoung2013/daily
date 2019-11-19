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
public interface SqlSession {

    int save(String sql) throws SQLException;
}
