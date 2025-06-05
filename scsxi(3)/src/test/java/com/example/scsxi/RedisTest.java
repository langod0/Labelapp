package com.example.scsxi;

import com.example.scsxi.service.CodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void testSendAndStoreCode() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("code:123456@qq.com", "123456");
    }

    @Test
    public void get(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String code = ops.get("code:123456@qq.com");
        System.out.println(code);
    }
}
