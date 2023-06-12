package com.lt.mapper;

import com.lt.pojo.Login;
import com.lt.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    //根据用户id查询
    User getUserById(@Param("userId") String userId);

    //查询所有用户
    List<User> getAllUser();

    //用户注册
    void register(@Param("schoolName") String schoolName, @Param("account") String account,
                  @Param("petName") String petName, @Param("userId") String userId, @Param("auth") int auth);

    //根据用户账号查询
    User getUserByUserAccount(@Param("userAccount") String userAccount);

    //用户结算，扣除金额
    int balancing(@Param("count") BigDecimal count, @Param("userId") String userId);

    int recharge(@Param("n") BigDecimal n,@Param("userId") String userId);

}
