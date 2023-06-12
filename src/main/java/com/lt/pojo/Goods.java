package com.lt.pojo;

import java.math.BigDecimal;

public class Goods {
    private String goodsId;
    private String goodsImg;
    private String goodsIntroduce;
    private BigDecimal price;
    private String sellUser;
    private String buyUser;
    private String uploadTime;
    private String exchangeTime;
    private String orderId;
    private int orderState;

    public Goods() {
    }


    public Goods(String goodsId, String goodsImg, String goodsIntroduce, BigDecimal price, String sellUser, String buyUser, String uploadTime, String exchangeTime, String orderId, int orderState) {
        this.goodsId = goodsId;
        this.goodsImg = goodsImg;
        this.goodsIntroduce = goodsIntroduce;
        this.price = price;
        this.sellUser = sellUser;
        this.buyUser = buyUser;
        this.uploadTime = uploadTime;
        this.exchangeTime = exchangeTime;
        this.orderId = orderId;
        this.orderState = orderState;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsIntroduce() {
        return goodsIntroduce;
    }

    public void setGoodsIntroduce(String goodsIntroduce) {
        this.goodsIntroduce = goodsIntroduce;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsIntroduce='" + goodsIntroduce + '\'' +
                ", price=" + price +
                ", sellUser='" + sellUser + '\'' +
                ", buyUser='" + buyUser + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", exchangeTime='" + exchangeTime + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
