package com.issac.spring.boot.mybatis.po;

import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-02
 * @desc:
 */
@Data
public class EmployeePo implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
