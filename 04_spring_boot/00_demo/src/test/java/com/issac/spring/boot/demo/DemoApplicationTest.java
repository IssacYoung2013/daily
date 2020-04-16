package com.issac.spring.boot.demo;

import com.issac.spring.boot.demo.event.WeatherRunListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private WeatherRunListener weatherRunListener;

    @Test
    public void contextLoad() {

    }

    @Test
    public void testEvent() {
        weatherRunListener.rain();
        weatherRunListener.snow();
    }
}