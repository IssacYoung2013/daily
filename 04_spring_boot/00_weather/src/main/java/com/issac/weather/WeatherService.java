package com.issac.weather;

/**
 * @author: ywy
 * @date: 2020-04-06
 * @desc:
 */
public class WeatherService {
    private WeatherSource weatherSource;

    public WeatherService() {
    }

    public WeatherService(WeatherSource weatherSource) {
        this.weatherSource = weatherSource;
    }

    public String getType() {
        return weatherSource.getType();
    }

    public String getRate() {
        return weatherSource.getRate();
    }

}
