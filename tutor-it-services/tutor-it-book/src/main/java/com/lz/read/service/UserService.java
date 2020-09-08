package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.User;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

public interface UserService {

    public RestResult listUser(String username,int pageNum,int pageSize);

    public RestResult updateUser(User user);

    public RestResult delUser(Integer id);

    public RestResult addUser(User user);
}
