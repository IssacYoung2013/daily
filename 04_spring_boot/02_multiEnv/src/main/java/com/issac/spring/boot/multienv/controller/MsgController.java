package com.issac.spring.boot.multienv.controller;

import com.issac.spring.boot.multienv.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-02
 * @desc:
 */
@RestController
public class MsgController {

    @Autowired
    MsgService msgService;

    @GetMapping("/some")
    public String someHandle() {
        return msgService.send();
    }
}
