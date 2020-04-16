package com.issac.spring.boot.demo.event;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
public class SnowEvent extends WeatherEvent {
    @Override
    String getWeather() {
        return "Snow";
    }
}
