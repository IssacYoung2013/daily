package com.issac.spring.boot.demo.pro;

import org.springframework.beans.factory.Aware;

/**
 * @author: ywy
 * @date: 2020-03-08
 * @desc:
 */
public interface MyAware extends Aware {
    void setFlag(Flag flag);
}
