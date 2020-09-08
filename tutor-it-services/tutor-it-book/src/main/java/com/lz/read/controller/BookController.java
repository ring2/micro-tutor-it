package com.lz.read.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lz.read.common.RestResult;
import com.lz.read.dao.BookresourceMapper;
import com.lz.read.pojo.Book;
import com.lz.read.pojo.Bookresource;
import com.lz.read.pojo.Booktype;
import com.lz.read.pojo.dto.BookDto;
import com.lz.read.service.BookService;
import com.lz.read.service.BooktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author :     lz
 * @date :       2020/4/2 08:50
 * description:   图书相关接口
 **/
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BooktypeService booktypeService;

    @Resource
    BookresourceMapper bookresourceMapper;

    @GetMapping("/list_no_type")
    public RestResult getNoTypeBook(int pageNum, int pageSize) {
        return bookService.getNoTypeBookList(pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public RestResult getBookById(@PathVariable Integer id) {
        return bookService.selBookById(id);
    }

    @PostMapping("/book_type")
    public RestResult addBookType(@RequestBody Booktype booktype) {
        return booktypeService.addBookType(booktype);
    }

    @GetMapping("/book_type/{pageNum}/{pageSize}")
    public RestResult getAllBookType(@PathVariable int pageNum, @PathVariable int pageSize) {
        return booktypeService.selAllBookType(pageNum, pageSize);
    }

    @GetMapping("/book_type")
    public RestResult getAllBookType1() {
        return booktypeService.selAllBookType1();
    }

    @DeleteMapping("/book_type/{id}")
    public RestResult delBookType(@PathVariable int id) {
        return booktypeService.delBookType(id);
    }

    @PutMapping("/book_type")
    public RestResult updateBookType(@RequestBody Booktype booktype) {
        return booktypeService.updateBookType(booktype);
    }

    @GetMapping("/list/{bookname}/{pageNum}/{pageSize}")
    public RestResult getBookList(@PathVariable String bookname, @PathVariable int pageNum, @PathVariable int pageSize) {
        return bookService.selBookList(bookname, pageNum, pageSize);
    }

    @PutMapping
    public RestResult update(@RequestBody BookDto book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public RestResult del(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/book_shelves/{id}/{type}")
    public RestResult cre(@PathVariable Integer id, @PathVariable Integer type) {
        return bookService.bookShelves(id, type);
    }

    @GetMapping("/book_by_type")
    public RestResult getBooksByType(@RequestParam Integer bookTypeId, @RequestParam(required = false) String isCharge,
                                     @RequestParam int pageNum, @RequestParam int pageSize) {
        return bookService.getBooksByType(bookTypeId, isCharge, pageNum, pageSize);
    }

    @PostMapping("/add_book")
    public RestResult addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @GetMapping("/no_reviewed")
    public RestResult noReviewed(){
        return bookService.getNoReviewed();
    }
}
