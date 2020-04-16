package com.issac.spring.boot.demo.ioc.ann;

import com.issac.spring.boot.demo.ioc.xml.Student;
import lombok.Data;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-04
 * @desc:
 */
@Component
@Data
//@DependsOn("student")
public class Worker {
    private String name;
}
