package com.issac.bfxy.order.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.issac.bfxy.store.service.api.HelloServiceApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: ywy
 * @date: 2019-11-16
 * @desc:
 */
@RestController
public class HelloController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.issac.bfxy.store.service.api.HelloServiceApi",
            check = false,
            timeout = 1000,
            retries = 0
    )
    private HelloServiceApi helloServiceApi;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) throws Exception {
        System.out.println("-------");
        return helloServiceApi.sayHello(name);
    }
}
