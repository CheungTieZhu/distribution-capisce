package com.capisce.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = false,includeFieldNames = true)
public class UserInfoEntity implements Serializable {
    @Getter @Setter private String userName;
    @Getter @Setter private String password;
    @Getter @Setter private String account;
}
