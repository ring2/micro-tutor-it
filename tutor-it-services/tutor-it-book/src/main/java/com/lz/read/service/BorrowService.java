package com.lz.read.service;

import com.lz.read.common.RestResult;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

public interface BorrowService {

    /**
     * 查询读者借阅信息
     * 有条件查询，例如超期归还、按期归还，借阅中，未归还等搜索条件
     */
    public RestResult selBorrowInfoByCondition(String condition,int pageNum,int pageSize);

    // TODO 对未按时归还的用户发送短信提醒/邮箱提醒
    public RestResult noticeUser();
}
