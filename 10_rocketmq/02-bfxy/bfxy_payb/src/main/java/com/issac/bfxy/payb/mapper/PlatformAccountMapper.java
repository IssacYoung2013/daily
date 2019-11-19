package com.issac.bfxy.payb.mapper;

import com.issac.bfxy.payb.entity.PlatformAccount;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PlatformAccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(PlatformAccount record);

    int insertSelective(PlatformAccount record);

    PlatformAccount selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(PlatformAccount record);

    int updateByPrimaryKey(PlatformAccount record);
}