package com.lz.read.pojo.vo;

import com.lz.read.pojo.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :     lz
 * @date :       2020/4/4 22:19
 * description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BookVo extends Book {
    private String btname;
}
