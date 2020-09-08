package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.read.common.RestResult;
import com.lz.read.config.BorrowCondition;
import com.lz.read.pojo.Borrow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lz.read.dao.BorrowMapper;
import com.lz.read.service.BorrowService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Override
    public RestResult selBorrowInfoByCondition( String condition,int pageNum,int pageSize) {
        Example example = new Example(Borrow.class);
//        example.createCriteria().andEqualTo("bwReaderid", userId);
        PageHelper.startPage(pageNum,40);
        List<Borrow> borrows = borrowMapper.selectAll();
        List<Borrow> conditionBorrows = new ArrayList<>();
        PageInfo pageInfo ;
        //TODO 需求变更，以下代码后续需要优化
        if (ObjectUtil.isNotEmpty(condition)) {
            borrows.forEach(borrow -> {
                Example example1 = new Example(Borrow.class);
                switch (BorrowCondition.getBorrowStatus(condition.trim())) {

                    // 正在借阅中
                    case BORROWING:
                         example1.createCriteria().andIsNull("bwBacktime").
                                andLessThanOrEqualTo("bwEndtime", new Date()).andEqualTo("id", borrow.getId());
                        break;
                    //准时归还
                    case RETURN_ON_TIME:
                        example1.createCriteria().andIsNotNull("bwBacktime").
                                andBetween("bwBacktime", borrow.getBwOuttime(), borrow.getBwEndtime()).andEqualTo("id", borrow.getId());
                        break;
                    //超期归还
                    case OVERDUE_RETURN:
                        example1.createCriteria().andIsNotNull("bwBacktime").
                                andGreaterThan("bwEndtime", borrow.getBwBacktime()).andEqualTo("id", borrow.getId());
                        break;
                    //超期未归还
                    case NO_RETURN:
                        example1.createCriteria().andIsNull("bwBacktime").
                                andGreaterThan("bwEndtime", new Date()).andEqualTo("id", borrow.getId());

                }
                Borrow borrow1 = borrowMapper.selectOneByExample(example1);
                if (borrow1 != null)
                    conditionBorrows.add(borrow1);
            });
            pageInfo = new PageInfo(conditionBorrows);
          return  RestResult.success(pageInfo);
        }else {
            pageInfo = new PageInfo(borrows);
           return RestResult.success(pageInfo);
        }
    }


    @Override
    public RestResult noticeUser() {
        // TODO
        return RestResult.failureOfParam();
    }
}
