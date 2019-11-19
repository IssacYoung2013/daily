package com.issac.spring.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@Component("dao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void update(String name, Double money) {
        // JDBC
        // Mybatis
        // JdbcTemplate
        template.update("update account set money = " + money + " WHERE name = '" + name + "'");
    }

    @Override
    public double queryMoney(String name) {
        Double money = template.queryForObject("select money from account where name = ?", new DoubleMapper(), name);
        return money;
    }

    class DoubleMapper implements RowMapper<Double> {
        @Override
        public Double mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getDouble("money");
        }
    }
}
