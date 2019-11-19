package com.issac.spring.ssm.po;

import lombok.Data;

import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
@Data
public class ItemQueryVo {
    Item item;
    List<Item> items;
}
