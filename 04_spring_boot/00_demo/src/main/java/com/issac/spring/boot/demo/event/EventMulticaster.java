package com.issac.spring.boot.demo.event;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
public interface EventMulticaster {
    void multicastEvent(WeatherEvent event);
    void addListener(WeatherListener listener);
    void removeListener(WeatherListener listener);
}
