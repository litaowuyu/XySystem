package com.lt.controller;
import com.lt.pojo.User;
import com.lt.service.UserService;
import com.lt.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Result result;


    /**
     * 用户注册
     */
    @RequestMapping("/user/register")
    public Map register(@RequestBody Map<String, String> map) {
        if (userService.register(map) == 1) {
            return result.success(1);
        } else {
            return result.error(0);
        }


    }

    /**
     * 通过user_id查询用户信息
     *
     * @param map
     * @return
     */
    @RequestMapping("/user/information")
    public Map getUserById(@RequestBody Map<String, String> map) {
        String userId = map.get("userId");
        //根据userId查询用户信息
        User user = userService.getUserById(userId);
        //向前端返回所查询用户信息
        return result.data(user);
    }

    /**
     * 通过user_account查询多个用户信息
     *
     * @param map
     * @return
     */
    @RequestMapping("/user/getUserInformationByUserAccount")
    public Map getUserByUserAccount(@RequestBody Map<String, String[]> map) {
        String[] arry = new String[]{};
        arry = map.get("userAccount");
        List list = new ArrayList();
        for (int i = 0; i < arry.length; i++) {
            //根据userId查询用户信息
            list.add(userService.getUserByUserAccount(arry[i]));
        }
        //向前端返回所查询用户信息
        return result.data(list);
    }
    /**
     * 通过user_account查询单个用户信息
     */
   @RequestMapping("/user/getUserByUserAccount")
   public Map getUserByOneUserAccount(@RequestBody Map<String,String> map){
       String userAccount = map.get("userAccount");
       return result.data(userService.getUserByUserAccount(userAccount));
   }
    /**
     * 获取所有用户信息
     */
    @RequestMapping("/root/allUser")
    public Map getAllUser() {
        List list = userService.getAllUser();
        return result.data(list);
    }

    /**
     * 买家结算订单
     */
    @RequestMapping("/user/balancing")
    public Map balancing(@RequestBody Map<String, Object> map) throws IOException {
        int a = userService.balancing(map);
        return result.success(a);
    }
    /**
     * 用户直接购买商品
     */
    @RequestMapping("/user/buy")
    public Map buy(@RequestBody Map<String,String> map){
        if(userService.buy(map)==1){
            return result.success(1);
        }else {
            return result.error(0);
        }
    }
    /**
     * 查询用户订单信息
     */
    @RequestMapping("/user/getOrders")
    public Map getOrders(@RequestBody Map<String,String> map){
        return result.data(userService.getOrders(map));
    }
    /**
     * 用户充值
     */
    @RequestMapping("/user/recharge")
    public Map recharge(@RequestBody Map<String,Object> map){
        if(userService.recharge(map)==1){
            return result.success(1);
        }else {
            return result.error(0);
        }

    }
}
