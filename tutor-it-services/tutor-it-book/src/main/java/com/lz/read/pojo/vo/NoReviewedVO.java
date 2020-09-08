package com.lz.read.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lz.read.pojo.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :     lz
 * @date :       2020/5/3 11:05
 * description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoReviewedVO extends Book {
    private String username;
    private String btName;
    private String reReason;
}
