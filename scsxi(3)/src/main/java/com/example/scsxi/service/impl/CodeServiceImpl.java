package com.example.scsxi.service.impl;

import com.example.scsxi.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.apache.commons.lang3.RandomStringUtils;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final JavaMailSender mailSender;
    private final StringRedisTemplate redisTemplate;
    private static final String CODE_KEY_PREFIX="code:";
    private static final long CODE_EXPIRE_TIME=5*60;
    @Override
    public boolean sendCode(String email) {
        String code=RandomStringUtils.randomNumeric(6);

        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("204013894@qq.com");
            message.setTo(email);
            System.out.println(code);
            message.setSubject("民权标注验证码");
            message.setText("您的验证码为："+code+"，5分钟内有效。");
            mailSender.send(message);
            redisTemplate.opsForValue().set(CODE_KEY_PREFIX+email,code,CODE_EXPIRE_TIME);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean checkCode(String email, String code) {
        String redisCode=redisTemplate.opsForValue().get(CODE_KEY_PREFIX+email);
        Integer size=redisCode.length();
        String lastSixChars = redisCode.substring(size - 6);
//        System.out.println(&quot;最后六位字符是: &quot; + lastSixChars);
        return lastSixChars!=null&&lastSixChars.equals(code);

    }
}
