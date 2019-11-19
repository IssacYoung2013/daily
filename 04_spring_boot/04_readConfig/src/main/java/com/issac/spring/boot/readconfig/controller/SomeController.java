package com.issac.spring.boot.readconfig.controller;

import com.issac.spring.boot.readconfig.dto.CountryDto;
import com.issac.spring.boot.readconfig.dto.GroupDto;
import com.issac.spring.boot.readconfig.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-01
 * @desc:
 */
@RestController
@PropertySource(value = "classpath:custom.properties",encoding = "utf8")
public class SomeController {

    @Value("${company.name}")
    private String companyName;

    @Value("${city.name}")
    private String cityName;

    @Value("${city.info}")
    private String cityInfo;

    @Autowired
    private StudentDto studentDto;

    @Autowired
    private CountryDto countryDto;

    @Autowired
    private GroupDto groupDto;

    @GetMapping("/some")
    public String someHandle() {
        return "Hello Spring Boot World!" + cityInfo;
    }

    @GetMapping("/some1")
    public StudentDto someHandle1() {
        return studentDto;
    }

    @GetMapping("/some2")
    public CountryDto someHandle2() {
        return countryDto;
    }

    @GetMapping("/some3")
    public GroupDto someHandle3() {
        return groupDto;
    }
}
