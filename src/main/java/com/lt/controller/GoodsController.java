package com.lt.controller;

import com.lt.service.GoodsService;
import com.lt.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private Result result;
    @RequestMapping("/goods/allGoods")
    public Map getAllGoods(){
        return result.data( goodsService.getAllGoods());
    }
    @RequestMapping("/goods/complete")
    public Map complete(@RequestBody Map<String,String> map){
        return result.success(goodsService.complete(map));
    }
    @PostMapping("/goods/uploadGoods")
    public Map upLoadGoods(@RequestParam("goodsDescription") String description,
                           @RequestParam("price")Double price,
                           @RequestParam("image") MultipartFile image,
                           @RequestParam("userId") String userId) throws IOException {
        if(goodsService.uploadGoods(description,price,image,userId)==1){
            return result.success(1);
        }else {
            return result.error(0);
        }

    }
}
