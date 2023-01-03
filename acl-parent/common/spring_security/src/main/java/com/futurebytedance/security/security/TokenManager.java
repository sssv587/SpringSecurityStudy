package com.futurebytedance.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2023/1/3 - 23:14
 * @Description token操作工具类
 */
@Component
public class TokenManager {
    //token有效时长
    private final static long tokenExpired = 24 * 60 * 60 * 1000;
    //编码秘钥
    private final static String tokenSingKey = "123456";

    //1 使用jwt 根据用户名生成token
    public String createToken(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpired))
                .signWith(SignatureAlgorithm.HS512, tokenSingKey)
                .compressWith(CompressionCodecs.GZIP).compact();
    }

    //2 根据token字符串得到用户信息
    public String getUserInfoFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenSingKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    //3 删除token
    public void removeToken(String token) {

    }
}
