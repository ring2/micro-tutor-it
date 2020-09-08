package com.lz.read.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lz.read.dao.AnswerMapper;
import com.lz.read.service.AnswerService;
import org.springframework.transaction.annotation.Transactional;

/**
    * @author :     lz
    * @date :       2020/4/1 23:55
    * description:  
 **/
  
@Service
@Transactional(rollbackFor = Exception.class)
public class AnswerServiceImpl implements AnswerService{

    @Resource
    private AnswerMapper answerMapper;

}
