package com.issac.spring.boot.thymeleaf;

import com.issac.spring.boot.thymeleaf.po.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Employee employee() {
        return new Employee();
    }
}
