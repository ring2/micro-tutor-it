package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Recommend;

/**
    * @author :     lz
    * @date :       2020/4/1 23:52
    * description:  
 **/
  
public interface RecommendService{

    /**
     *  查看专家已审核或未审核的书籍，
     */
    public RestResult getRecommendBook(Byte status,Integer expertId,int pageNum,int pageSize);

    /**
     *  修改recommend相关信息：审核状态、审核意见等
     */
    public RestResult updateRecommend(Recommend recommend);

    public Recommend selRecommendById(Integer id);

    RestResult del(Integer id);

    RestResult getReviewedBooks(Integer expertId);

    RestResult getReviewedRecommend(Integer pageNum,Integer pageSize);
}
