package com.lz.read.dao;

import com.lz.read.pojo.Expert;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExpertMapper extends Mapper<Expert> {
    List<Integer> getExpertIds(Integer btId);
}