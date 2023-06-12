package com.lt.controller;

import com.lt.service.OrderService;
import com.lt.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private Result result;
    @RequestMapping("/order/deleteOrder")
    public Map deleteOrder(@RequestBody Map<String,String> map){
        if(orderService.deleteOrder(map)==1){
            return result.success(1);
        }else {
            return result.error(0);
        }
    }
}
