package com.example.springbootjwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    // 加密密钥
    private static final String SECRET = "12132";
    // 过期时间7天
    private static final int EXPIRE = 7;


    /**
     * 生成token
     * @param payload claims
     * @return token
     */
    public static String getToken(Map<String, String> payload) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, EXPIRE);
        JWTCreator.Builder builder = JWT.create();
        payload.forEach((k, v) -> {
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }


    /**
     * 校验token
     * @param token
     * @return DecodedJWT
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}
