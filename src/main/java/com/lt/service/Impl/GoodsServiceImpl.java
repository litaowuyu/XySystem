package com.lt.service.Impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.lt.mapper.GoodsMapper;
import com.lt.pojo.User;
import com.lt.service.GoodsService;
import com.lt.service.UserService;
import com.lt.tools.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private Upload upload;
    @Autowired
    private UserService userService;

    /**
     * 获取所有商品信息
     * @return
     */
    @Override
    public List getAllGoods() {
        return  goodsMapper.getAllGoods();
    }

    @Override
    public Integer complete(Map<String, String> map) {
        String orderId = map.get("orderId");
        return goodsMapper.complete(orderId);
    }

    @Override
    public int uploadGoods(String description, Double price, MultipartFile image, String userId) throws IOException {
        String imageName = upload.upload(image,"goodsImg");
        BigDecimal newPrice = new BigDecimal(price);
        Date date = new Date();
        //获取当前时间，作为上传时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = userService.getUserById(userId);
        return goodsMapper.uploadGoods(description,newPrice,imageName,user.getUserAccount(),sdf.format(date), NanoIdUtils.randomNanoId());
    }
}
