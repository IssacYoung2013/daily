package com.issac.spring.boot.demo.except;

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-16
 * @desc:
 */
public class MyExceptionReporter implements SpringBootExceptionReporter {
    private ConfigurableApplicationContext context;

    MyExceptionReporter(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public boolean reportException(Throwable failure) {
        if (failure instanceof UnsatisfiedDependencyException) {
            UnsatisfiedDependencyException unsatisfiedDependencyException
                    = (UnsatisfiedDependencyException) failure;
            System.out.println("no such bean " + unsatisfiedDependencyException.getInjectionPoint()
                    .getField().getName());
        }
        return false;
    }
}
