package com.example.scsxi.exception;

import com.example.scsxi.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handler(Exception e) {
        e.printStackTrace();
        return Result.fail(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败");
    }
}
