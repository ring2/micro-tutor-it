package com.lz.read.pojo.dto;

import com.lz.read.pojo.Book;
import lombok.Data;

/**
 * @author :     lz
 * @date :       2020/4/14 23:10
 * description:
 **/
@Data
public class BookDto extends Book {
    private Integer bookResourceId;
}
