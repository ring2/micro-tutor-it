package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.UserBooktokenRel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lz.read.dao.UserBooktokenRelMapper;
import com.lz.read.service.UserBooktokenRelService;

/**
 * @author :     lz
 * @date :       2020/4/2 08:02
 * description:
 **/

@Service
public class UserBooktokenRelServiceImpl implements UserBooktokenRelService {

    @Resource
    private UserBooktokenRelMapper userBooktokenRelMapper;

    @Override
    public RestResult addBookToken(Integer userId, Integer bookTokenId) {
        if (ObjectUtil.isNotEmpty(userId) && ObjectUtil.isNotEmpty(bookTokenId)) {
            UserBooktokenRel userBooktokenRel = new UserBooktokenRel();
            userBooktokenRel.setUserId(userId);
            userBooktokenRel.setBookTokenId(bookTokenId);
            if (userBooktokenRelMapper.insert(userBooktokenRel) > 0) {
                return RestResult.success();
            } else
                return RestResult.failure("为用户添加书券失败");
        }
        return RestResult.failureOfParam();
    }
}
