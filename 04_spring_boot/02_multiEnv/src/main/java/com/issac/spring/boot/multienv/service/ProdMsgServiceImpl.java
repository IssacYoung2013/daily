package com.issac.spring.boot.multienv.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-02
 * @desc:
 */
@Service
@Profile("pro")
public class ProdMsgServiceImpl implements MsgService {
    @Override
    public String send() {
        System.out.println("生产环境发送消息");
        return "pro";
    }
}
