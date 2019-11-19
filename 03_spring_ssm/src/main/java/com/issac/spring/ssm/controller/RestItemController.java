package com.issac.spring.ssm.controller;

import com.issac.spring.ssm.po.Item;
import com.issac.spring.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
@RestController
@RequestMapping("item")
public class RestItemController {

    @Autowired
    ItemService service;

    @RequestMapping("queryItemById2")
    public Item queryItemById() {
        return service.queryItemById(2);
    }
}
