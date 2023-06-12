package com.lt.mapper;

import com.lt.pojo.ShoppingBag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingBagMapper {
    //根据用户Id查询用户购物车
    List<ShoppingBag> getShoppingBagByUserId(@Param("userId") String userId);
    //删除商品
    int deleteBag(@Param("id") String id);

    int addShoppingBag(@Param("userId") String userId, @Param("goodsId") String goodsId);
}
