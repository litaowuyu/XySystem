package com.lt.controller;

import com.lt.service.LoginService;
import com.lt.tools.JWT;
import com.lt.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JWT jwt;
    @Autowired
    private Result result;
    /**
     * 用户登录
     */
    @RequestMapping("/user/login")
    public Map login(
            @RequestBody Map<String,String> map
    ){

        String userAccount = map.get("userAccount");
        String password = map.get("password");
        if(loginService.login(userAccount,password)==1){
            String userId = loginService.getUserId(userAccount);
            String token = jwt.token(userId);
            return result.login(token,userId);
        }else{
            return result.error(loginService.login(userAccount,password));
        }
    }
}
