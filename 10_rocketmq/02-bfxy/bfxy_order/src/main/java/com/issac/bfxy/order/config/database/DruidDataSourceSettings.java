package com.issac.bfxy.order.config.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 *
 * @author: ywy
 * @date: 2019-11-16
 * @desc:
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidDataSourceSettings {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private int initialSize;
    private int minIdle;
    private int maxIdle;
    private int maxActive;
    private int maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private boolean useGlobalDataSourceStat;
    private Properties connectionProperties;
}
