package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.dto.InvestigateDto;
import com.lz.read.pojo.vo.AnswerSituationVo;
import com.lz.read.pojo.vo.UserAnswerVo;
import com.lz.read.service.InvestigateService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/16 14:50
 * description:  调查问卷
 **/
@RestController
@RequestMapping("/investigate")
public class InvestigateController {

    @Autowired
    InvestigateService investigateService;
    // 发布调查问卷
    @PostMapping
    public RestResult postInvestigate(@RequestBody InvestigateDto investigateDto) {
       return  investigateService.postInvestigate(investigateDto);
    }

    //查询调查问卷的用户回答情况
    @GetMapping("/situation")
    public RestResult<UserAnswerVo> getSituation(){
        return investigateService.getSituation();
    }

    //单选题
    @GetMapping("/situation1")
    public RestResult<List<AnswerSituationVo>> getSituation1(){
        return investigateService.getSituation1();
    }

    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Integer id) {
        return investigateService.delete(id);
    }
}
