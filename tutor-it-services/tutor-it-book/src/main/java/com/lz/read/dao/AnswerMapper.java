package com.lz.read.dao;

import com.lz.read.pojo.Answer;
import com.lz.read.pojo.vo.AnswerSituationVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AnswerMapper extends Mapper<Answer> {

    List<AnswerSituationVo> selAnswerSituation();
}