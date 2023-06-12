package com.lt.tools;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Result {
    /**
     * 登陆成功分发token
     */
    public Map login(String token,String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        map.put("token",token);
        map.put("userId",userId);
        return map;
    }
    /**
     * 请求执行成功返回信息
     * @param statusCode
     * @return
     */
    public  Map success(Integer statusCode) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code",statusCode);
        resultMap.put("message","success");
        return resultMap;
    }

    /**
     * 请求执行失败返回信息
     * @param statusCode
     * @return
     */
    public  Map error(Integer statusCode) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code",statusCode);
        resultMap.put("message","error");
        return resultMap;
    }

    /**
     * 请求数据返回的数据
     * @param data
     * @return
     */
    public  Map data(Object data)  {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message","success");
        resultMap.put("data",data);
        resultMap.put("code",1);
        return resultMap;
    }
}
