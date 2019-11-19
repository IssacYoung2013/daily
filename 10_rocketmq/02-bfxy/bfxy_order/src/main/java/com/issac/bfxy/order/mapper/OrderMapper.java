package com.issac.bfxy.order.mapper;

import com.issac.bfxy.order.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateOrderStatus(@Param("orderId") String orderId,
                          @Param("orderStatus") String status,
                          @Param("updateBy") String updateBy,
                          @Param("updateTime") Date currentTime);
}