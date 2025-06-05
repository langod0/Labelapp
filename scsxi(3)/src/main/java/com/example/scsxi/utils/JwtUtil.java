package com.example.scsxi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String JWT_SECRET = "scsxi";

    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims",  claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }
    public static Map<String, Object> verify(String token) {
        return JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
