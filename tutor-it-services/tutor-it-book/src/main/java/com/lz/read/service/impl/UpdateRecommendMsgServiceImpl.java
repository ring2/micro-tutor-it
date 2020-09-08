package com.lz.read.service.impl;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.UpdateRecommendMsg;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lz.read.dao.UpdateRecommendMsgMapper;
import com.lz.read.service.UpdateRecommendMsgService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
    * @author :     ring2
    * @date :       2020/4/21 20:46
    * description:  
 **/
  
@Service
public class UpdateRecommendMsgServiceImpl implements UpdateRecommendMsgService{

    @Resource
    private UpdateRecommendMsgMapper updateRecommendMsgMapper;

    @Override
    public RestResult getUnReadMsg() {
        Example example = new Example(UpdateRecommendMsg.class);
        example.createCriteria().andEqualTo("isRead",false);
        List<UpdateRecommendMsg> updateRecommendMsgs = updateRecommendMsgMapper.selectByExample(example);
        return RestResult.success(updateRecommendMsgs);
    }

    @Override
    public RestResult updateReadMsg(UpdateRecommendMsg updateRecommendMsg) {
        updateRecommendMsg.setIsRead(true);
        updateRecommendMsgMapper.updateByPrimaryKey(updateRecommendMsg);
        return RestResult.success();
    }
}
