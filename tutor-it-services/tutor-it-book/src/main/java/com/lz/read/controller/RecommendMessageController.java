package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.UpdateRecommendMsg;
import com.lz.read.service.UpdateRecommendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     ring2
 * @date :       2020/4/21 20:58
 * description:
 **/
@Slf4j
@RestController
@RequestMapping("/rmd_msg")
public class RecommendMessageController {

    @Autowired
    UpdateRecommendMsgService service;

    @GetMapping()
    public RestResult getUnReadMsg(){
        return service.getUnReadMsg();
    }

    @PutMapping
    public RestResult updateReadMsg(@RequestBody UpdateRecommendMsg updateRecommendMsg){
        return service.updateReadMsg(updateRecommendMsg);
    }
}
