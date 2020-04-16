package com.issac.spring.boot.demo.ioc.ann;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-04
 * @desc:
 */
@Data
@Component
public class Teacher {
    private String name;
}
