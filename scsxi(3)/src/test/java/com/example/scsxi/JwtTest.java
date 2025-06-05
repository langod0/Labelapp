package com.example.scsxi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","test_name");
        //生成token
        String  token =  JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*3))//添加过期时间
                .sign(Algorithm.HMAC256("twentyeight"));

        System.out.println(token);
    }
}
