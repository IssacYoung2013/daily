package com.issac.spring.boot.demo.controller;

import com.issac.spring.boot.demo.bean.Demo;
import com.issac.spring.boot.demo.service.DemoService;
import com.issac.spring.boot.demo.service.TestService;
import com.issac.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    DemoService demoService;

    @Resource
    TestService testService;

    @RequestMapping("/hello/{id}")
    public String hello(@PathVariable Integer id) {
        return Optional.ofNullable(demoService.getDemoById(id))
                .map(Demo::toString)
                .orElse("empty string");
    }

    @RequestMapping("/test")
    public String test() {
        return testService.test();
    }

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("weather")
    @ResponseBody
    public String weather() {
        return weatherService.getType() + ","+ weatherService.getRate();
    }
}
