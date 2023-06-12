package com.lt.mapper;

import com.lt.pojo.Login;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    //用户登陆验证
    Login login(@Param("userAccount") String userAccount);

    //注册
    void register(@Param("account") String account, @Param("password") String password);
}
