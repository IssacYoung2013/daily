package com.issac.spring.mvc.controller;

import com.issac.spring.mvc.bean.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器开发方式多种，实现 HttpRequestHandler 接口、Controller 接口、注解方式
 * 注解方式：
 *  1. 类加上 @Controller
 *  2. 类和方法必须 @RequestMapping
 *
 * @author: ywy
 * @date: 2019-10-29
 * @desc:
 */
@Controller
public class ItemController {

    @RequestMapping("queryItem")
    public ModelAndView item() {
        ModelAndView mv = new ModelAndView();

        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setName("华为手机");
        item1.setDetail("HuaWei P30 Pro");
        item1.setPrice(5000f);

        Item item2 = new Item();
        item2.setName("苹果手机");
        item2.setDetail("iPhone11 Pro");
        item2.setPrice(7000f);

        Item item3 = new Item();
        item3.setName("联想笔记本");
        item3.setDetail("Lenovo notebook");
        item3.setPrice(11000f);

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        // 设置数据模型,相当于Request setAttribute
        mv.addObject("items",itemList);
//        mv.setViewName("/WEB-INF/jsp/item-list.jsp");
        mv.setViewName("item-list");
        return mv;
    }
}
