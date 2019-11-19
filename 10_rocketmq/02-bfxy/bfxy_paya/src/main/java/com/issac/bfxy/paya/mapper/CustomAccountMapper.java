package com.issac.bfxy.paya.mapper;

import com.issac.bfxy.paya.entity.CustomAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface CustomAccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(CustomAccount record);

    int insertSelective(CustomAccount record);

    CustomAccount selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(CustomAccount record);

    int updateByPrimaryKey(CustomAccount record);

    int updateBalance(@Param("accountId") String accountId
            , @Param("newBalance") BigDecimal newBalance
            , @Param("version") int currentVersion
            , @Param("updateTime") Date updateTime);
}