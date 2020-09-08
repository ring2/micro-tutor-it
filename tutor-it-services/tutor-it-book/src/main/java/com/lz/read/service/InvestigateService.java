package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.dto.InvestigateDto;
import com.lz.read.pojo.vo.AnswerSituationVo;
import com.lz.read.pojo.vo.UserAnswerVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/16 18:58
 * description:
 **/
public interface InvestigateService {
    RestResult postInvestigate(InvestigateDto investigateDto);
    RestResult<UserAnswerVo> getSituation();
    RestResult<List<AnswerSituationVo>> getSituation1();

    RestResult delete(Integer id);
}
