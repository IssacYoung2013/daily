package com.issac.spring.service.impl;

import com.issac.spring.service.BaseService;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class BaseServiceImpl implements BaseService {
    @Override
    public String doSome() {
        return "Hello Mike";
    }
}
