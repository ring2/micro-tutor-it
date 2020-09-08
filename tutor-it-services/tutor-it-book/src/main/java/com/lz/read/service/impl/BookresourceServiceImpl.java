package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Bookresource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lz.read.dao.BookresourceMapper;
import com.lz.read.service.BookresourceService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class BookresourceServiceImpl implements BookresourceService {

    @Resource
    private BookresourceMapper bookresourceMapper;

    @Override
    public RestResult addBookResource(Bookresource bookresource) {
        if (ObjectUtil.isNotEmpty(bookresource)) {
            int i = bookresourceMapper.insertSelective(bookresource);
            if (i > 0)
                return RestResult.success();
            else
                return RestResult.failure("添加图书资源失败");
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult delBookResource(Integer id) {
        if (id != null) {
            int i = bookresourceMapper.deleteByPrimaryKey(id);
            if (i > 0)
                return RestResult.success();
            else
                return RestResult.failure("删除图书资源失败");
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult updateBookResource(Bookresource bookresource) {
        if (ObjectUtil.isNotEmpty(bookresource)) {
            int i = bookresourceMapper.updateByPrimaryKeySelective(bookresource);
            if (i > 0)
                return RestResult.success();
            else
                return RestResult.failure("更新图书资源失败");
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult listBookResource(Byte isCharge) {
        Example example = new Example(Bookresource.class);
        // isCharge == 0 : 不收费
        example.createCriteria().andEqualTo("rsCharge", isCharge);
        List<Bookresource> bookresources = bookresourceMapper.selectByExample(example);
        return RestResult.success(bookresources);
    }
}
