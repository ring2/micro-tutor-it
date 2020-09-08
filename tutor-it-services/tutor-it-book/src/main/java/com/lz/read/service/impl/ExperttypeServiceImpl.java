package com.lz.read.service.impl;

import com.lz.read.common.RestResult;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lz.read.dao.ExperttypeMapper;
import com.lz.read.service.ExperttypeService;
import org.springframework.transaction.annotation.Transactional;

/**
    * @author :     lz
    * @date :       2020/4/1 23:55
    * description:  
 **/
  
@Service
@Transactional(rollbackFor = Exception.class)
public class ExperttypeServiceImpl implements ExperttypeService{

    @Resource
    private ExperttypeMapper experttypeMapper;

    @Override
    public RestResult getExpertType() {
        return RestResult.success(experttypeMapper.selectAll());
    }
}
