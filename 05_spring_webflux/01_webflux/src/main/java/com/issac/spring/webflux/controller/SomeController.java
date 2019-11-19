package com.issac.spring.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: ywy
 * @date: 2019-11-04
 * @desc:
 */
@RestController
@Slf4j
public class SomeController {

    @GetMapping("/common")
    public String commonHandle() {
        log.info("common start");
        String result = doSome("common handle");
        log.info("common end");
        return result;
    }

    @GetMapping("/mono")
    public Mono monoHandle() {
        log.info("mono start");
        Mono<String> mono = Mono.fromSupplier(() ->
                doSome("mono handler")
        );
        log.info("mono end");
        return mono;
    }

    @GetMapping("/flux")
    public Flux fluxHandle() {
        return Flux.just("北京", "上海", "杭州");
    }

    /**
     * http://localhost:9090/array?cities=北京,上海,杭州
     *
     * @param cities
     * @return
     */
    @GetMapping("/array")
    public Flux fluxHandle2(String[] cities) {
        // 数组转换为 flux
        return Flux.fromArray(cities);
    }

    /**
     * http://localhost:9090/list?cities=北京,上海,杭州
     *
     * @param cities
     * @return
     */
    @GetMapping("/list")
    public Flux fluxHandle3(@RequestParam List<String> cities) {
        // List转为Stream Stream转换为 flux
        return Flux.fromStream(cities.stream());
    }

    /**
     * http://localhost:9090/list?cities=北京,上海,杭州
     *
     * @param cities
     * @return
     */
    @GetMapping("/time")
    public Flux fluxHandle4(@RequestParam List<String> cities) {
        log.info("flux start");

        // List转为Stream Stream转换为 flux
        Flux<String> flux = Flux.fromStream(cities.stream()
                .map(i -> doSome("elem-" + i)));
        log.info("flux end");
        return flux;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/sse",produces = "text/event-stream")
    public Flux fluxHandle5() {
        return Flux.just("北京", "上海", "杭州");
    }

    /**
     * 耗时操作
     *
     * @param msg
     * @return
     */
    private String doSome(String msg) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        return msg;
    }

}
