package com.lt.service.Impl;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lt.mapper.GoodsMapper;
import com.lt.mapper.LoginMapper;
import com.lt.mapper.OrderMapper;
import com.lt.mapper.UserMapper;
import com.lt.pojo.Goods;
import com.lt.pojo.User;
import com.lt.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;


    /**
     * 通过user_id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    /**
     * 根据用户账号获取用户信息
     *
     * @return
     */
    @Override
    public User getUserByUserAccount(String userAccount) {
        return userMapper.getUserByUserAccount(userAccount);
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @Override
    public List getAllUser() {
        return userMapper.getAllUser();
    }

    /**
     * 用户注册
     *
     * @param map
     * @return
     */
    @Override
    public int register(Map<String, String> map) {
        String schoolName = map.get("schoolName");
        String petName = map.get("petName");
        String password = map.get("pass");
        String account = map.get("account");
        int auth = 1;
        //使用nanoId随机生成用户Id
        String userId = NanoIdUtils.randomNanoId();
        //判断用户账户是否已经存在
        if (null == userMapper.getUserByUserAccount(account)) {
            //不存在，正常注册
            userMapper.register(schoolName, account, petName, userId, auth);
            loginMapper.register(account, password);
            return 1;
        } else {
            //已存在，返回0，代表账户已经存在
            return 0;
        }
    }

    /**
     * 用户结算订单，扣除对应金额
     * @param map
     * @return
     */
    @Override
    public int balancing(Map<String, Object> map) throws JsonProcessingException {
        //取出用户结算商品列表JSON字符串
        String a = (String) map.get("checks");
        //通过jackson中的ObjectMapper将JSON字符串反序列化为java对象
        ObjectMapper obj = new ObjectMapper();
        //忽略未知的JSON字段
        obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //将字符串转化为java对象数组
        Goods[] checks = obj.readValue(a, Goods[].class);
        String userId = (String) map.get("userId");
        //计算需要扣除的金额
        BigDecimal count = new BigDecimal(0);
        for (int i = 0; i < checks.length; i++) {
            Goods good = checks[i];
            count = count.add(good.getPrice());
        }
        //判断用户的余额是否足够
        User user = getUserById(userId);
        if (user.getBalance().subtract(count).compareTo(BigDecimal.ZERO) < 0) {
            //用户余额不足，返回0，代表余额不足
            return 0;
        } else {
            //用户余额足够,扣除金额，返回1，代表结算成功
            userMapper.balancing(count, userId);
            //修改商品信息
            Date date = new Date();
            //获取当前时间，作为交易时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int j = 0; j < checks.length; j++) {
                //生成订单号
                String orderId = NanoIdUtils .randomNanoId();
                Goods good = checks[j];
                goodsMapper.balancing(sdf.format(date), userId, good.getGoodsId(),orderId);
                orderMapper.add(orderId,good.getGoodsId());
            }
            return 1;
        }

    }

    @Override
    public List<Goods> getOrders(Map<String, String> map) {
        String userId = map.get("userId");
        System.out.println("到这了！！！！！！！！！！！！！");
        return goodsMapper.getOrders(userId);
    }

    @Override
    public int recharge(Map<String, Object> map) {
        Integer n1 = (Integer) map.get("recharge");
        BigDecimal n = new BigDecimal(n1);
        String userId = (String) map.get("userId");
        return userMapper.recharge(n,userId);
    }

    @Override
    public int buy(Map<String, String> map) {
       String goodsId = map.get("goodsId");
       String userId = map.get("userId");
       String orderId = NanoIdUtils .randomNanoId();
        Date date = new Date();
        //获取当前时间，作为交易时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i = goodsMapper.balancing(sdf.format(date),userId,goodsId,orderId);
        int j =  orderMapper.add(orderId,goodsId);
        if(i==1 && j==1){
            return 1;
        }else {
            return 0;
        }

    }
}
