package com.issac.spring.boot.demo.event;

import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Component
public class RainListener implements WeatherListener {
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if(event instanceof RainEvent) {
            System.out.println("hello,"+event.getWeather());
        }
    }
}
