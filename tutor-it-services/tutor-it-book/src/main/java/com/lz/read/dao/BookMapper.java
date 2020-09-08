package com.lz.read.dao;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Book;
import com.lz.read.pojo.vo.BookVo;
import com.lz.read.pojo.vo.NoReviewedVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BookMapper extends Mapper<Book> {

   List<BookVo> selBookWithBookType(@Param("bookname") String bookname);

   //获取未审核图书列表
    List<NoReviewedVO> getNoReviewed();
}