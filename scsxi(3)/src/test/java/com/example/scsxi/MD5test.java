package com.example.scsxi;

import com.example.scsxi.utils.Md5Util;
import org.junit.jupiter.api.Test;

public class MD5test {
    @Test
    public void md(){
        String code="123456";
        String Md5= Md5Util.getMD5(code);
        System.out.println(Md5);
    }
}
