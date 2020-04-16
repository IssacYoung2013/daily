package com.issac.weather;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: ywy
 * @date: 2020-04-06
 * @desc:
 */
@ConfigurationProperties(prefix = "weather")
public class WeatherSource {
    private String type;

    private String rate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
