package com.lt.controller;

import com.lt.service.Impl.ShoppingBagServiceImpl;
import com.lt.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ShoppingBagController {
    @Autowired
    ShoppingBagServiceImpl shoppingBagService;
    @Autowired
    Result result;
    @RequestMapping("/ShoppingBag")
    public Map getShoppingBagByUserId(@RequestBody Map<String,String> map){
        return result.data(shoppingBagService.getShoppingBagByUserId(map.get("userId")));
    }
    @RequestMapping("/user/deleteBag")
    public Map deleteBag(@RequestBody Map<String,String> map){
        if(shoppingBagService.deleteBag(map)==1){
            return result.success(1);
        }
        else {
            return result.error(0);
        }
    }
    @RequestMapping("/user/addShoppingBag")
    public Map addShoppingBag(@RequestBody Map<String,String> map){
        if(shoppingBagService.addShoppingBag(map)==1){
            return result.success(1);
        }else{
            return result.error(0);
        }
    }

}
