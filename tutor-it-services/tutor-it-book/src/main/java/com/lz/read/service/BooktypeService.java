package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Booktype;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

public interface BooktypeService {

    RestResult selAllBookType(int pageNum, int pageSize);

    RestResult selAllBookType1();

    RestResult delBookType(int id);

    RestResult updateBookType(Booktype booktype);

    RestResult addBookType(Booktype booktype);
}
