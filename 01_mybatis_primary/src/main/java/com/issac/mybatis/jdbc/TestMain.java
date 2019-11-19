package com.issac.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author: ywy
 * @date: 2019-10-20
 * @desc:
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=rootroot");
        conn.setAutoCommit(false);
        PreparedStatement pStatement = conn.prepareStatement("insert into dept values(?,?,?)");
        pStatement.setInt(1, 1009);
        pStatement.setString(2, "test");
        pStatement.setString(3, "test");
        try {

            pStatement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
        } finally {
            if (pStatement != null) {
                pStatement.close();
            }
            conn.close();
        }
    }
}
