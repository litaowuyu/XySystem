package com.lt.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GoodsService {
    List getAllGoods();

    Integer complete(Map<String, String> map);

    int  uploadGoods(String description,Double price, MultipartFile image,String userId) throws IOException;
}
