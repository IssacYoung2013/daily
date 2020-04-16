package com.issac.spring.boot.demo.ioc.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    String name;
    Integer age;
    List<String> courseList;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", courseList=" + String.join(",", courseList) +
                '}';
    }
}
