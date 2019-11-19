package com.issac.mybatis.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
public class MyTypeHandler implements TypeHandler {

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("BooleanTypeConverter");

        if (parameter == null) {
            // flag == null
            ps.setInt(i, 0);
            return;
        }
        Boolean flag = (Boolean) parameter;
        if (flag == true) {
            ps.setInt(i, 1);
        } else {
            ps.setInt(i, 0);
        }
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        System.out.println("BooleanTypeConverter");

        int flag = rs.getInt(columnName);
        Boolean myFlag = Boolean.FALSE;
        if (flag == 1) {
            myFlag = Boolean.TRUE;
        }
        return myFlag;
    }

    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        System.out.println("BooleanTypeConverter");

        return null;
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("BooleanTypeConverter");

        return null;
    }
}
