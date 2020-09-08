package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Booktoken;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

public interface BooktokenService {

    /**
     * @return 对书卷的管理
     */

    public RestResult listBookToken(int pageNum,int pageSize);

    public RestResult updateBookToken(Booktoken booktoken);

    public RestResult delBookToken(Integer id);

    public RestResult addBookToken(Booktoken booktoken);

}
