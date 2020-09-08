package com.lz.read.service;

import com.lz.read.common.RestResult;

import java.util.Map;

/**
 * @author :     lz
 * @date :       2020/4/2 10:16
 * description:
 **/
public interface LoginService {
     RestResult<Map> validateUser(String username, String password, String userType);
}
