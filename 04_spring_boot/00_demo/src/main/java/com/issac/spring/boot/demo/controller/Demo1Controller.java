package com.issac.spring.boot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author: ywy
 * @date: 2020-04-16
 * @desc:
 */
@RestController
public class Demo1Controller {

    @GetMapping("demo")
    public Mono<String> demo() {
        return Mono.just("demo");
    }
}
