package com.issac.bfxy.store.service.provider;

import com.issac.bfxy.store.service.api.HelloServiceApi;
import com.alibaba.dubbo.config.annotation.Service;
/**
 *
 * @author: ywy
 * @date: 2019-11-16
 * @desc:
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class HelloServiceProvider implements HelloServiceApi {
    @Override
    public String sayHello(String name) {
        System.out.println("------name:"+name);
        return "hello " + name;
    }
}
