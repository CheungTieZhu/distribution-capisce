package com.capisce.entity;

import lombok.*;

import java.io.Serializable;

@Data
public class UserInfoEntity implements Serializable {
    private String userName;
    private String password;
    private String account;
}
