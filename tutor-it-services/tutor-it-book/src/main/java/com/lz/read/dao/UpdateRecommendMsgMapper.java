package com.lz.read.dao;

import com.lz.read.pojo.Recommend;
import com.lz.read.pojo.UpdateRecommendMsg;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UpdateRecommendMsgMapper extends Mapper<UpdateRecommendMsg> {

    UpdateRecommendMsg selSomethingInsertToUpdateRecommendMsg(@Param("recommendMsg")Recommend recommend);
}