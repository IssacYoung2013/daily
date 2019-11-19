package com.issac.webflux.router.advice;

import com.issac.webflux.router.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @author: ywy
 * @date: 2019-11-06
 * @desc: @ControllerAdvice 表示当前类为切面，连接点为处理器方法
 */
@ControllerAdvice
public class ParameterValidateAdvice {

    @ExceptionHandler
    public ResponseEntity<String> validateHandle(StudentException ex) {
        String msg = ex.getMessage() + "【" + ex.getErrField() + ":" + ex.getErrMsg() + "】";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> validateHandle(WebExchangeBindException ex) {
        return new ResponseEntity<String>(exToStr(ex), HttpStatus.BAD_REQUEST);
    }


    private String exToStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
                .reduce("", (a, b) -> a + "\n" + b);
    }

}
