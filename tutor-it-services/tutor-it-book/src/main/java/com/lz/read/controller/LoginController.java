package com.lz.read.controller;

import cn.hutool.core.util.StrUtil;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.vo.CaptchaVo;
import com.lz.read.service.LoginService;
import com.lz.read.service.UserService;
import com.lz.read.utils.RedisUtil;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author :     lz
 * @date :       2020/4/2 08:45
 * description:  登录逻辑相关接口
 **/
@RestController
public class LoginController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginService loginService;

    /**
     * @return 根据用户的账号密码及验证码进行校验登陆并校验验证码
     */
    @PostMapping("/login")
    public RestResult<Map> login(@RequestBody CaptchaVo captchaVo) {
        String username = captchaVo.getUsername();
        String password = captchaVo.getPassword();
        String key = captchaVo.getKey();
        String result = captchaVo.getResult();
        // 后端同样需要验证,用户名密码不能为空
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password) || StrUtil.isEmpty(key)) {
            return RestResult.failureOfParam();
        }
        // 首先判断验证码是否有效
        if (StrUtil.isNotEmpty(result) && redisUtil.get(key).equals(result.trim())) {
            return loginService.validateUser(username, password, captchaVo.getUserType());
        }
        return RestResult.failureOfCaptcha();
    }

    // 获取验证码,随机生成标识该验证码的key 设置进redis 5分钟失效
    @GetMapping("/captcha")
    public RestResult getCaptcha() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 40);
        captcha.setLen(2);  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        String answer = captcha.text();// 获取运算的结果：5
        String s = captcha.toBase64();
        String key = UUID.randomUUID().toString();
        redisUtil.setEx(key, answer, 5, TimeUnit.MINUTES);
        return RestResult.success(new CaptchaVo().setKey(key).setCaptcha(s));
    }
}
