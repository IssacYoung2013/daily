package com.issac.spring.boot.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-01
 * @desc:
 */
@Controller
public class SomeController {

    @GetMapping("/some")
    public String someHandle() {
        return "page/welcome" ;
    }

}
