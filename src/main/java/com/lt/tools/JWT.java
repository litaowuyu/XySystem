package com.lt.tools;



import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JWT {
    //过期时长
    private long time = 1000*60*60*24*7;
    //签名
    private String signature = "user";

    /**
     * 加密方法
     * @param userId
     * @param auth
     * @return
     */
    public String token(String userId){
        //创建JWT对象
        JwtBuilder jwtBuilder = Jwts.builder();

        String token = jwtBuilder
                //设置第一部分Header
                //token类型
                .setHeaderParam("typ","JWT")
                //算法
                .setHeaderParam("alg","HS256")
                //设置第二部分PayLoad
                //携带的信息
                .claim("userId",userId)
                //设置有效时间
                .setExpiration(new Date(System.currentTimeMillis()+time))
                //设置JWT的标识
                .setId(UUID.randomUUID().toString())
                //设置第三部分Signature
                //设置加密算法，签名
                .signWith(SignatureAlgorithm.HS256,signature)
                //拼接
                .compact();
        return token;
   }

    /**
     * 解密
     * @param token
     * @return
     */
    public Map parse(String token){
        //创建JWT解密对象
        JwtParser jwtParser = Jwts.parser();
        //根据签名解密
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        //获取信息体
        Claims claims = claimsJws.getBody();
        Map map = new HashMap<>();
        //获取所携带的信息
        map.put("userId",claims.get("userId"));
        map.put("auth",claims.get("auth"));
        map.put("life",claims.getExpiration());
        return map;
    }

}
