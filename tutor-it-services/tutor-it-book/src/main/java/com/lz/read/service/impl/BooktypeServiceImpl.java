package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Booktype;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lz.read.dao.BooktypeMapper;
import com.lz.read.service.BooktypeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
    * @author :     lz
    * @date :       2020/4/1 23:55
    * description:  
 **/
  
@Service
@Transactional(rollbackFor = Exception.class)
public class BooktypeServiceImpl implements BooktypeService{

    @Resource
    private BooktypeMapper booktypeMapper;


    @Override
    public RestResult selAllBookType(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Booktype> booktypes = booktypeMapper.selectAll();
        PageInfo pageInfo = new PageInfo(booktypes);
        return RestResult.success(pageInfo);
    }

    @Override
    public RestResult selAllBookType1() {
        return RestResult.success(booktypeMapper.selectAll());
    }

    @Override
    public RestResult delBookType(int id) {
        int i = booktypeMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return RestResult.success();
        }
        return RestResult.failure("删除图书类别失败");
    }

    @Override
    public RestResult updateBookType(Booktype booktype) {
        if (ObjectUtil.isNotEmpty(booktype)) {
            int i = booktypeMapper.updateByPrimaryKey(booktype);
            if (i > 0) {
                return RestResult.success();
            }else
                return RestResult.failure("更新图书类别失败");
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult addBookType(Booktype booktype) {
        if (ObjectUtil.isNotEmpty(booktype)) {
            int i = booktypeMapper.insertSelective(booktype);
            if (i > 0) {
                return RestResult.success();
            }else
                return RestResult.failure("新增图书类别失败");
        }
        return RestResult.failureOfParam();
    }
}
