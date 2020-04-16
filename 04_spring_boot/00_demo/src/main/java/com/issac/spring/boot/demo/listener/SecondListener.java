package com.issac.spring.boot.demo.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Order(2)
public class SecondListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("Hello Second");
    }
}
