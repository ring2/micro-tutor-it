package com.lz.read.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.read.common.RestResult;
import com.lz.read.dao.BookresourceMapper;
import com.lz.read.pojo.Book;
import com.lz.read.pojo.Bookresource;
import com.lz.read.pojo.Books;
import com.lz.read.pojo.Recommend;
import com.lz.read.pojo.dto.BookDto;
import com.lz.read.pojo.vo.BookVo;
import com.lz.read.pojo.vo.NoReviewedVO;
import com.lz.read.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lz.read.dao.BookMapper;
import com.lz.read.service.BookService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private BooksServiceImpl booksService;

    @Resource
    BookresourceMapper bookresourceMapper;

    //更新图书
    @Override
    public RestResult updateBook(BookDto book) {
        // 获取资源id
        Integer bookResourceId = book.getBookResourceId();
        // 修改book
        Book book1 = new Book();
        BeanUtil.copyProperties(book, book1);
        Books books = new Books();
        BeanUtil.copyProperties(book, books);
        booksService.updateBookType(books);
        bookMapper.updateByPrimaryKeySelective(book1);
        if (bookResourceId != null) {
            Bookresource bookresource = bookresourceMapper.selectByPrimaryKey(bookResourceId);
            bookresource.setBookId(book1.getId());
            bookresourceMapper.updateByPrimaryKey(bookresource);
        }
        return RestResult.success();
    }

    @Override
    public RestResult selBookById(Integer id) {
        return RestResult.success(bookMapper.selectByPrimaryKey(id));
    }

    //查询图书列表
    @Override
    public RestResult selBookList(String bookname, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = null;
        bookname = bookname.trim();
        if (StrUtil.isNotEmpty(bookname)) {
            List<BookVo> books = bookMapper.selBookWithBookType(bookname);
            pageInfo = new PageInfo(books);
            return RestResult.success(pageInfo);
        }
        List<BookVo> books = bookMapper.selBookWithBookType(bookname);
        pageInfo = new PageInfo(books);
        return RestResult.success(pageInfo);
    }

    //删除书籍
    @Override
    public RestResult deleteBook(Integer bookId) {
        if (ObjectUtil.isNotEmpty(bookId)) {
            int i = bookMapper.deleteByPrimaryKey(bookId);
            booksService.delBookType(bookId);
            if (i > 0) {
                return RestResult.success();
            } else {
                return RestResult.failure("删除书籍失败");
            }
        }
        return RestResult.failureOfParam();
    }

    //推荐图书上、下架管理
    @Override
    public RestResult bookShelves(Integer recommendId, Integer type) {
        // type == 0 上架  type == 1 下架
        Recommend recommend = recommendService.selRecommendById(recommendId);
        if (ObjectUtil.isNotEmpty(recommend)) {
            Book book = new Book();
            book.setId(recommend.getReBookid());
            if (type.equals(0)) {
                book.setBookShelves(1);
            } else {
                book.setBookShelves(0);
            }
            int i = bookMapper.updateByPrimaryKeySelective(book);
            if (i > 0) {
                return RestResult.success();
            } else {
                return RestResult.failure("推荐书籍上架失败");
            }
        }
        return RestResult.failureOfParam();
    }

    //获取图书列表（没有选择类型的情况下）
    @Override
    public RestResult getNoTypeBookList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(Book.class);
        example.createCriteria().andIsNull("bookTypeId");
        PageInfo pageInfo = new PageInfo(bookMapper.selectByExample(example));
        return RestResult.success(pageInfo);
    }

    //根据图书类型查询图书列表
    @Override
    public RestResult getBooksByType(Integer bookTypeId, String isCharge, int pageNum, int pageSize) {
        Boolean charge = false;
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(isCharge)) {
            if (isCharge.equals("收费")) {
                charge = true;
            }
            criteria.andEqualTo("isCharge", charge);
        }
        criteria.andEqualTo("bookTypeId", bookTypeId);
        PageInfo pageInfo = new PageInfo(bookMapper.selectByExample(example));
        return RestResult.success(pageInfo);
    }

    //图书阅读榜
    @Override
    public RestResult selRankForRead() {
        PageHelper.startPage(1, 10);
        Example example = new Example(Book.class);
        example.orderBy("bookreadnum").desc();
        List<Book> books = bookMapper.selectByExample(example);
        return RestResult.success(books);
    }

    //新增图书
    @Override
    public Integer insertBook(Book book) {
        bookMapper.insertSelective(book);
        Example example = new Example(Book.class);
        example.createCriteria().andEqualTo("bookname", book.getBookname());
        List<Book> books = bookMapper.selectByExample(example);
        Integer id = books.get(0).getId();
        Books bookss = new Books();
        BeanUtil.copyProperties(book, bookss);
        booksService.addBookType(bookss);
        return id;
    }


    //添加图书资源
    @Override
    public RestResult addBook(BookDto bookDto) {
        // 获取资源id，为该资源添加bookid
        Integer bookResourceId = bookDto.getBookResourceId();
        // 新增book
        Book book = new Book();
        BeanUtil.copyProperties(bookDto, book);
        Integer integer = insertBook(book);
        Bookresource bookresource = bookresourceMapper.selectByPrimaryKey(bookResourceId);
        bookresource.setBookId(integer);
        bookresourceMapper.updateByPrimaryKey(bookresource);
        return RestResult.success();
    }

    //获取审核未通过图书列表
    @Override
    public RestResult getNoReviewed() {
        List<NoReviewedVO> noReviewed = bookMapper.getNoReviewed();
        return RestResult.success(noReviewed);
    }

}
