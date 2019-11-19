package com.issac.bfxy.order.mapper;

import com.issac.bfxy.order.entity.CustomAccount;

public interface CustomAccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(CustomAccount record);

    int insertSelective(CustomAccount record);

    CustomAccount selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(CustomAccount record);

    int updateByPrimaryKey(CustomAccount record);
}