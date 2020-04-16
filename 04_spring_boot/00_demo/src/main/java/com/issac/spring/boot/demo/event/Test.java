package com.issac.spring.boot.demo.event;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        EventMulticaster eventMulticaster = new WeatherEventMulticaster();
        WeatherListener rain = new RainListener();
        WeatherListener snow = new SnowListener();
        eventMulticaster.addListener(rain);
        eventMulticaster.addListener(snow);
        eventMulticaster.multicastEvent(new SnowEvent());
        eventMulticaster.multicastEvent(new RainEvent());
        eventMulticaster.removeListener(snow);
        eventMulticaster.multicastEvent(new SnowEvent());
        eventMulticaster.multicastEvent(new RainEvent());
    }
}
