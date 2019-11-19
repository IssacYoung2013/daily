package com.issac.webflux.router.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: ywy
 * @date: 2019-11-06
 * @desc:
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentException extends RuntimeException {
    private String errField;
    private String errMsg;

    public StudentException(String message, String errField, String errMsg) {
        super(message);
        this.errField = errField;
        this.errMsg = errMsg;
    }

}
