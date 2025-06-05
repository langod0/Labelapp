package com.example.scsxi.pojo;

import lombok.Data;

@Data
public class Upload {
    private String name;
    private byte[] file;
    private String email;
    private String worker;
    private String done;
    private String progress;
}
