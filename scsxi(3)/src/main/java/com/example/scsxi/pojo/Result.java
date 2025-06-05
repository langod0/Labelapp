package com.example.scsxi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//状态码 0成功 1失败
    private String msg;//提示信息
    private T data;//数据

    public static <E> Result<E> success(E data){
        return new Result<>(0, "success", data);
    }
    public static <E> Result<E> fail(String msg){
        return new Result<>(1, msg, null);
    }
}
