package com.lt.service.Impl;

import com.lt.mapper.ShoppingBagMapper;
import com.lt.service.ShoppingBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingBagServiceImpl implements ShoppingBagService{
    @Autowired
    private ShoppingBagMapper shoppingBagMapper;

    /**
     * 通过用户Id获取用户购物车商品
     * @param userId
     * @return
     */
    @Override
    public List getShoppingBagByUserId(String userId) {

            return shoppingBagMapper.getShoppingBagByUserId(userId);

    }

    @Override
    public int deleteBag(Map<String, String> map) {
        String id = map.get("goodsId");
        return shoppingBagMapper.deleteBag(id);
    }

    @Override
    public int addShoppingBag(Map<String, String> map) {
        String userId = map.get("userId");
        String goodsId = map.get("goodsId");
        return shoppingBagMapper.addShoppingBag(userId,goodsId);
    }
}
