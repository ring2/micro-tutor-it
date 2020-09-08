package com.lz.read.config;

import lombok.Getter;

/**
 * @author :     lz
 * @date :       2020/4/2 10:12
 * description:
 **/
@Getter
public enum UserType {
    ADMIN("管理员"),
    EXPERT("专家");
    private String userType;

    UserType(String userType) {
        this.userType = userType;
    }
}
