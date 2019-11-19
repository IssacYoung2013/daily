package com.issac.spring.ssm.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义编译时异常
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
@Getter
@Setter
public class CustomException extends Exception {

    private String msg;

    public CustomException(String msg) {
        this.msg = msg;
    }
}
