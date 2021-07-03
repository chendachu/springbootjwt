package com.example.springbootjwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

class SpringbootjwtApplicationTests {

    @Test
    void contextLoads() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,1000);
        String sign = JWT.create().withClaim("username", "haihong")
                .withClaim("id", 1)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("123"));// 签名
        System.out.println(sign);

    }

    @Test
    public void testVerify(){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjI1MjQxODUxLCJ1c2VybmFtZSI6ImhhaWhvbmcifQ.mUFor4aDBwsWUrzN4vyVx3PQ4p8h7kIGeg4XDXUy6QE");
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getClaim("id").asInt());
        System.out.println(""+verify.getExpiresAt());
    }

}
