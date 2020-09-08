package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.dao.RecommendMapper;
import com.lz.read.pojo.vo.RecommendVo;
import com.lz.read.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/14 09:45
 * description:
 **/
@RestController
@Slf4j
public class RankController {

    @Autowired
    BookService bookService;

    @Resource
    RecommendMapper recommendMapper;

    //图书阅读排行榜
    @GetMapping("/rank/read")
    public RestResult selRankForRead(){
        return bookService.selRankForRead();
    }

    //读者推荐排行榜
    @GetMapping("/rank/recommend")
    public RestResult getRankByRecommendNum(){
        List<RecommendVo> rankByRecommendNum = recommendMapper.getRankByRecommendNum();
        return RestResult.success(rankByRecommendNum);
    }

}
