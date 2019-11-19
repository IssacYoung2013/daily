package com.issac.mybatis.implproxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class Invocation implements InvocationHandler {

    private SqlSession obj;

    public Invocation(SqlSession obj) {
        this.obj = obj;
    }

    private Connection connection;
    private PreparedStatement ps;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        init(args);
        Field field = obj.getClass().getDeclaredField("ps");
        field.setAccessible(true);
        field.set(obj,ps);
        Object result = method.invoke(obj, args);
        close();

        return result;
    }

    private void init(Object[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:///test?useUnicode=true", "root", "rootroot");
        ps = connection.prepareStatement(args[0].toString());
    }

    private void close() throws SQLException {
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
