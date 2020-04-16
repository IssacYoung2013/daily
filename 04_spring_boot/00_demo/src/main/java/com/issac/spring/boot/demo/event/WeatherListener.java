package com.issac.spring.boot.demo.event;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
public interface WeatherListener {
    void onWeatherEvent(WeatherEvent event);
}
