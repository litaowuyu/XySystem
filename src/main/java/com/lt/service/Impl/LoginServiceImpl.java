package com.lt.service.Impl;

import com.lt.mapper.LoginMapper;
import com.lt.mapper.UserMapper;
import com.lt.pojo.Login;
import com.lt.pojo.User;
import com.lt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @return
     */
    @Override
    public int login(String userAccount, String password) {
        Login login = loginMapper.login(userAccount);
        //判断是否为空来判断用户是否存在
        if(login==null)
        {
            return 2;
        }
        //存在，判断密码是否正确
        else if(login.getUserPassword().equals(password))
        {
            return 1;
        }
        //否则就是密码错误
        else
        {
            return 0;
        }
    }

    /**
     * 通过用户账户查询用户信息
     * @return
     */
    @Override
    public String getUserId(String userAccount) {
       User user = userMapper.getUserByUserAccount(userAccount);
        return user.getUserId();
    }
}
