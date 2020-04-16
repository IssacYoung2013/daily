package com.issac.spring.boot.demo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Component
public class WeatherRunListener {

    @Autowired
    WeatherEventMulticaster eventMulticaster;

    public void snow() {
        eventMulticaster.multicastEvent(new SnowEvent());
    }

    public void rain() {
        eventMulticaster.multicastEvent(new RainEvent());
    }
}
