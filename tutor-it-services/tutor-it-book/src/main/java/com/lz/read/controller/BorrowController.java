package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Borrow;
import com.lz.read.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/2 08:50
 * description:   用户借阅信息相关接口
 **/
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    /**
     * 查询读者借阅信息
     * 有条件查询，例如超期归还、按期归还，借阅中，未归还等搜索条件
     */
    @GetMapping("/borrow_situation/{condition}/{pageNum}/{pageSize}")
    public RestResult get(@PathVariable String condition, @PathVariable int pageNum, @PathVariable int pageSize) {
        return borrowService.selBorrowInfoByCondition(condition, pageNum, pageSize);
    }
}
