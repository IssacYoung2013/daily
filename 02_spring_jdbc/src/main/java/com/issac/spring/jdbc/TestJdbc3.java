package com.issac.spring.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-27
 * @desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring_1.xml")
public class TestJdbc3 {

    @Autowired
    JdbcTemplate template;

    @Test
    public void test() {
        template.execute("insert into account(name,money) VALUES ('测试3',10000)");
    }
}
