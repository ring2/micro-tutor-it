package com.lz.read.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :     lz
 * @date :       2020/4/2 09:02
 * description:
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CaptchaVo {
    private String key;
    private String result;
    private String username;
    private String password;
    private String captcha;
    private String userType;
}
