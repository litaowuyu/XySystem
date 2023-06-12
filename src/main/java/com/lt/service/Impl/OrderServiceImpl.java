package com.lt.service.Impl;

import com.lt.mapper.OrderMapper;
import com.lt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int deleteOrder(Map<String,String> map) {
        String orderId = map.get("orderId");
        return orderMapper.deleteOrder(orderId);
    }
}
