package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.UpdateRecommendMsg;

/**
    * @author :     ring2
    * @date :       2020/4/21 20:46
    * description:  
 **/
  
public interface UpdateRecommendMsgService{

    RestResult getUnReadMsg();

    RestResult updateReadMsg(UpdateRecommendMsg updateRecommendMsg);
}
