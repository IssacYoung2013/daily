package com.issac.spring.boot.demo.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-07
 * @desc:
 */
@Component
@Order(1)
public class FirstCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\u001B[32m >>> startup first runner <<<");
    }
}
