package com.issac.spring.boot.webdemo.controller;

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
public class SomeController {

    @GetMapping("/some")
    public String someHandle() {
        return "Hello Spring Boot World!";
    }

}
