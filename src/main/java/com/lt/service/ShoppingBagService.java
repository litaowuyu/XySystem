package com.lt.service;

import java.util.List;
import java.util.Map;

public interface ShoppingBagService {
    //通过用户Id获取用户购物车商品
    List getShoppingBagByUserId(String userId);

    int deleteBag(Map<String, String> map);

    int addShoppingBag(Map<String, String> map);
}
