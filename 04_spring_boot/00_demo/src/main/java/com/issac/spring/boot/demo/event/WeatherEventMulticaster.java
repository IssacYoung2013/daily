package com.issac.spring.boot.demo.event;

import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Component
public class WeatherEventMulticaster extends AbstractEventMulticaster {

    @Override
    protected void doEnd() {
        System.out.println("end of broadcast weather event");
    }

    @Override
    protected void doStart() {
        System.out.println("begin broadcast weather event");
    }
}
