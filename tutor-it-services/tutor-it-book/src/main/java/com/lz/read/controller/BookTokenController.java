package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Booktoken;
import com.lz.read.service.BooktokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/2 08:51
 * description:  书券相关接口
 **/
@RestController
@RequestMapping("/bookToken")
public class BookTokenController {

    @Autowired
    BooktokenService booktokenService;

    @GetMapping("/list/{pageNum}/{pageSize}")
    public RestResult get(@PathVariable int pageNum,@PathVariable int pageSize) {
        return booktokenService.listBookToken( pageNum, pageSize);
    }

    @PutMapping
    public RestResult update(@RequestBody Booktoken booktoken) {
        return booktokenService.updateBookToken(booktoken);
    }

    @DeleteMapping("/{id}")
    public RestResult del(@PathVariable Integer id) {
        return booktokenService.delBookToken(id);
    }

    @PostMapping
    public RestResult cre(@RequestBody Booktoken booktoken) {
        return booktokenService.addBookToken(booktoken);
    }
}
