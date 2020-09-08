package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Book;
import com.lz.read.pojo.dto.BookDto;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

public interface BookService {


    /**
     * @param book
     * @return 修改书籍信息、分类
     */
    RestResult updateBook(BookDto book);

    RestResult selBookById(Integer id);

    /**
     * 图书列表
     */
    RestResult selBookList(String bookname, int pageNum, int pageSize);

    RestResult deleteBook(Integer bookId);

    /**
     * 并对审核通过和未通过的书籍进行上架处理；
     * 上架处理理解： 选择上架则是修改book表中的上架状态，1表示上架。
     */
    RestResult bookShelves(Integer recommendId, Integer type);

    /**
     * @return 获取未分类的列表
     */
    RestResult getNoTypeBookList(int pageNum, int pageSize);


    /**
     * @return 返回通过类别id获取图书列表
     */
    RestResult getBooksByType(Integer bookType, String isCharge, int pageNum, int pageSize);


    //图书阅读榜
    RestResult selRankForRead();

    Integer insertBook(Book book);

    RestResult addBook(BookDto bookDto);

    RestResult getNoReviewed();
}
