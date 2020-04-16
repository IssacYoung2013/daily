package com.issac.spring.boot.demo.event;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
public class RainEvent extends WeatherEvent {
    @Override
    String getWeather() {
        return "rain";
    }
}
