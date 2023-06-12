package com.lt.service;

public interface LoginService {
    //用户登录验证
    int login(String userAccount,String password);
    String getUserId(String userAccount);
}
