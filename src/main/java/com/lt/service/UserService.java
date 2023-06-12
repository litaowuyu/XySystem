package com.lt.service;

import com.lt.pojo.Goods;
import com.lt.pojo.User;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserService {
    //根据用户id查询用户信息
    User getUserById(String userId);
    //根据用户账号查询用户信息
    User getUserByUserAccount(String userAccount);
    //查询所有用户信息
    List getAllUser();
    //用户注册
    int register(Map<String,String> map);

    int  balancing(Map<String, Object> map) throws IOException;

    List<Goods> getOrders(Map<String, String> map);

    int recharge(Map<String, Object> map);

    int buy(Map<String, String> map);
}
