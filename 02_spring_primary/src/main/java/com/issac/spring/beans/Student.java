package com.issac.spring.beans;

import lombok.Data;
import lombok.ToString;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
@ToString
public class Student {
    private String sname;
    private Integer age;
    private Teacher teacher;
}
