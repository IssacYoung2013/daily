package com.issac.bfxy.payb.mapper;

import com.issac.bfxy.payb.entity.Packaged;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagedMapper {
    int deleteByPrimaryKey(String packageId);

    int insert(Packaged record);

    int insertSelective(Packaged record);

    Packaged selectByPrimaryKey(String packageId);

    int updateByPrimaryKeySelective(Packaged record);

    int updateByPrimaryKey(Packaged record);
}