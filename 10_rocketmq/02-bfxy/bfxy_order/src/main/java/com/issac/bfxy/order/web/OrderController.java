package com.issac.bfxy.order.web;

import com.issac.bfxy.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @HystrixCommand(
            commandKey = "/createOrder",
            commandProperties = {
                    @HystrixProperty(name = "execution.timeout.enabled", value = "true")
                    // 超时时间
                    , @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            fallbackMethod = "createOrderFallbackMethod4Timeout"
    )
    @RequestMapping("/createOrder")
    public String createOrder(@RequestParam("cityId") String cityId,
                              @RequestParam("platformId") String platformId,
                              @RequestParam("userId") String userId,
                              @RequestParam("suppliedId") String suppliedId,
                              @RequestParam("goodsId") String goodsId) throws Exception {
//        TimeUnit.SECONDS.sleep(5);
        return orderService.createOrder(cityId,platformId,userId,suppliedId,goodsId)?"下单成功":"下单失败";
//        return "下单成功";
    }

    public String createOrderFallbackMethod4Timeout(@RequestParam("cityId") String cityId,
                                                    @RequestParam("platformId") String platformId,
                                                    @RequestParam("userId") String userId,
                                                    @RequestParam("suppliedId") String suppliedId,
                                                    @RequestParam("goodsId") String goodsId) throws Exception {
        System.out.println("-----超时降级策略执行-------");
        return "hystrix timeout";
    }

    @HystrixCommand(
            commandKey = "createOrder1",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            },
            threadPoolKey = "createOrderThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    // 尽量设置的大
                    @HystrixProperty(name = "maxQueueSize", value = "20000"),
                    // 动态设置Rejection 阈值大小
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
            },
            fallbackMethod = "createOrderFallbackMethod4Thread"
    )
    @RequestMapping("/createOrder1")
    public String createOrder1(@RequestParam("cityId") String cityId,
                               @RequestParam("platformId") String platformId,
                               @RequestParam("userId") String userId,
                               @RequestParam("suppliedId") String suppliedId,
                               @RequestParam("goodsId") String goodsId) throws Exception {
        return "下单成功";
    }

    public String createOrderFallbackMethod4Thread(@RequestParam("cityId") String cityId,
                                                   @RequestParam("platformId") String platformId,
                                                   @RequestParam("userId") String userId,
                                                   @RequestParam("suppliedId") String suppliedId,
                                                   @RequestParam("goodsId") String goodsId) throws Exception {
        System.out.println("-----限流降级策略执行-------");
        return "hystrix thread";
    }

    @HystrixCommand(
            commandKey = "createOrder2",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
                    , @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "3")
            },
            fallbackMethod = "createOrderFallbackMethod4Semaphore"
    )
    @RequestMapping("/createOrder2")
    public String createOrder2(@RequestParam("cityId") String cityId,
                               @RequestParam("platformId") String platformId,
                               @RequestParam("userId") String userId,
                               @RequestParam("suppliedId") String suppliedId,
                               @RequestParam("goodsId") String goodsId) throws Exception {
        return "下单成功";
    }

    public String createOrderFallbackMethod4Semaphore(@RequestParam("cityId") String cityId,
                                                      @RequestParam("platformId") String platformId,
                                                      @RequestParam("userId") String userId,
                                                      @RequestParam("suppliedId") String suppliedId,
                                                      @RequestParam("goodsId") String goodsId) throws Exception {
        System.out.println("-----信号量降级策略执行-------");
        return "hystrix semaphore";
    }
}
