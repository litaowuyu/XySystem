package com.lt.pojo;

public class ShoppingBag {
    private String goodsId;
    private String userId;

    public ShoppingBag() {
    }

    public ShoppingBag(String goodsId, String userId) {
        this.goodsId = goodsId;
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ShoppingBag{" +
                "goodsId='" + goodsId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
