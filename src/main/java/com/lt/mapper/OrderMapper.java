package com.lt.mapper;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int deleteOrder(@Param("orderId") String orderId);

    int add(@Param("orderId") String orderId, @Param("userId") String userId);
}
