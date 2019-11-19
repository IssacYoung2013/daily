package com.issac.bfxy.paya.mapper;

import com.issac.bfxy.paya.entity.BrokerMessageLog;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerMessageLogMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);
}