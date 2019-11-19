package com.issac.spring.ssm.service.impl;

import com.issac.spring.ssm.mapper.ItemMapper;
import com.issac.spring.ssm.po.Item;
import com.issac.spring.ssm.po.ItemExample;
import com.issac.spring.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ywy
 * @date: 2019-10-30
 * @desc:
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper mapper;

    @Override
    public List<Item> queryItemList() {
        ItemExample example = new ItemExample();
        ItemExample.Criteria criteria = example.createCriteria();

        return mapper.selectByExample(example);
    }

    @Override
    public Item queryItemById(Integer id) {
        if (id == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateItem(Item item) {
        if(item == null || item.getId() == null) {
            return;
        }
        mapper.updateByPrimaryKeySelective(item);
    }
}
