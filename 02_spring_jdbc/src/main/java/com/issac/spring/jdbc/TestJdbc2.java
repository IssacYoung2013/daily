package com.issac.spring.jdbc;

import com.issac.spring.jdbc.bean.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class TestJdbc2 {

    @Autowired
    JdbcTemplate template;

    @Test
    public void test() {
        template.execute("insert into account(name,money) VALUES ('测试1',10000)");
    }

    @Test
    public void test1() {
        List<Account> accountList = template.query("SELECT * FROM account", new MyBeanMapper(), null);
        System.out.println(accountList);
    }

    class MyBeanMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getDouble("money"));
            return account;
        }
    }
}
