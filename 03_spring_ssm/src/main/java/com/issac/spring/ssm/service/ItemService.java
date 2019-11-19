package com.issac.spring.ssm.service;

import com.issac.spring.ssm.po.Item;

import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-30
 * @desc:
 */
public interface ItemService {
    List<Item> queryItemList();
    Item queryItemById(Integer id);
    void updateItem(Item item);
}
