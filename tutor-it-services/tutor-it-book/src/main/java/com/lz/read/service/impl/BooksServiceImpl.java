package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lz.read.common.RestResult;
import com.lz.read.dao.BooksMapper;
import com.lz.read.pojo.Books;
import com.lz.read.pojo.Booktype;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author :     ring2
 * @date :       2020/5/21 20:31
 * description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BooksServiceImpl {

    @Resource
    BooksMapper booksMapper;


    public RestResult delBookType(int id) {
        int i = booksMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return RestResult.success();
        }
        return RestResult.failure("删除失败");
    }


    public RestResult updateBookType(Books booktype) {
        if (ObjectUtil.isNotEmpty(booktype)) {
            int i = booksMapper.updateByPrimaryKey(booktype);
            if (i > 0) {
                return RestResult.success();
            }else
                return RestResult.failure("更新失败");
        }
        return RestResult.failureOfParam();
    }


    public RestResult addBookType(Books booktype) {
        if (ObjectUtil.isNotEmpty(booktype)) {
            int i = booksMapper.insertSelective(booktype);
            if (i > 0) {
                return RestResult.success();
            }else
                return RestResult.failure("新增失败");
        }
        return RestResult.failureOfParam();
    }
}
