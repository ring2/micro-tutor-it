package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Booktoken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lz.read.dao.BooktokenMapper;
import com.lz.read.service.BooktokenService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class BooktokenServiceImpl implements BooktokenService {

    @Resource
    private BooktokenMapper booktokenMapper;


    @Override
    public RestResult listBookToken(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = null;
        List<Booktoken> booktokens;
        booktokens = booktokenMapper.selectAll();
        pageInfo = new PageInfo(booktokens);
        return RestResult.success(pageInfo);
    }

    @Override
    public RestResult updateBookToken(Booktoken booktoken) {
        if (ObjectUtil.isNotEmpty(booktoken)) {
            int i = booktokenMapper.updateByPrimaryKeySelective(booktoken);
            if (i > 0)
                return RestResult.success();
            else
                return RestResult.failure("更新书卷失败");
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult delBookToken(Integer id) {
        if (ObjectUtil.isNotEmpty(id)) {
            int i = booktokenMapper.deleteByPrimaryKey(id);
            if (i > 0)
                return RestResult.success();
            else
                return RestResult.failure("删除书卷失败");
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult addBookToken(Booktoken booktoken) {
        if (ObjectUtil.isNotEmpty(booktoken)) {
            int i = booktokenMapper.insertSelective(booktoken);
            if (i > 0)
                return RestResult.success();
            else
                return RestResult.failure("添加书卷失败");
        }
        return RestResult.failureOfParam();
    }
}
