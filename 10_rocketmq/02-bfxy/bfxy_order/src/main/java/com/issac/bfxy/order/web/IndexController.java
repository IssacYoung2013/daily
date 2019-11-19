package com.issac.bfxy.order.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ywy
 * @date: 2019-11-16
 * @desc:
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() throws Exception {
        System.out.println("---------");
        return "index";
    }
}
