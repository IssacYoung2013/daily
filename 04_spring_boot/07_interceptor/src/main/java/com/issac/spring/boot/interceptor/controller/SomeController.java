package com.issac.spring.boot.interceptor.controller;

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

    @GetMapping("/first/some")
    public String firstHandle() {
        return "first";
    }

    @GetMapping("/first/aaa")
    public String aaaHandle() {
        return "aaa";
    }


    @GetMapping("/second/other")
    public String secondHandle() {
        return "second";
    }

    @GetMapping("/third/other")
    public String thirdHandle() {
        return "third";
    }
}
