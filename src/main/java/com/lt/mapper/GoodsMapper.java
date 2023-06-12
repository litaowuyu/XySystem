package com.lt.mapper;

import com.lt.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsMapper {
    //查询所有商品信息
    List<Goods> getAllGoods();

    int balancing(@Param("date") String date, @Param("userId") String userId,@Param("goodsId") String goodsId,@Param("orderId") String orderId);

    List<Goods> getOrders(@Param("userId") String userId);

    Integer complete(@Param("orderId") String orderId);

    int uploadGoods(@Param("description") String description,@Param("price") BigDecimal price,@Param(("imageName")) String imageName,@Param("userAccount") String userAccount,@Param("uploadTime") String uploadTime,@Param("goodsId") String goodsId);
}
