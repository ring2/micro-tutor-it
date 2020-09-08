package com.lz.read.service;

import com.lz.read.common.RestResult;

/**
 * @author :     lz
 * @date :       2020/4/2 08:02
 * description:
 **/

public interface UserBooktokenRelService {

    /**
     * 为用户添加书券
     */
    public RestResult addBookToken(Integer userId,Integer bookTokenId);
}
