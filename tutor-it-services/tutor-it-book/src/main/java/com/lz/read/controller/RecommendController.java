package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Recommend;
import com.lz.read.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/4 19:44
 * description:
 **/
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    // 根据审核状态获取推荐的书籍
    @GetMapping("/list/{reStatus}/{expertId}/{pageNum}/{pageSize}")
    public RestResult getRecommendBookByStatus(@PathVariable Byte reStatus, @PathVariable Integer expertId,@PathVariable int pageNum, @PathVariable int pageSize) {
        return recommendService.getRecommendBook(reStatus, expertId,pageNum, pageSize);
    }

    // 获取已审核的书籍
    @GetMapping("/reviewed/{expertId}")
    public RestResult getReviewedBooks(@PathVariable Integer expertId) {
        return recommendService.getReviewedBooks(expertId);
    }

    // 提交书籍审核意见、修改书籍审核结果
    @PutMapping
    public RestResult updateRecommend(@RequestBody Recommend recommend) {
        return recommendService.updateRecommend(recommend);
    }

    @DeleteMapping("/{id}")
    public RestResult deleteRecommend(@PathVariable Integer id) {
        return recommendService.del(id);
    }

    @GetMapping("/listReviewed/{pageNum}/{pageSize}")
    public RestResult getReviewedRecommend(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        return recommendService.getReviewedRecommend(pageNum,pageSize);
    }


}
