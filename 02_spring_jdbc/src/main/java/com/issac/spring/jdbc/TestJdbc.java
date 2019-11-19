package com.issac.spring.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
public class TestJdbc {

    @Test
    public void test() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///test?useUnicode=true");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("insert into account(name,money) VALUES ('测试',10000)");
    }
}
