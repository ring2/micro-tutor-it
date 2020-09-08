package com.lz.read.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author :     ring2
 * @date :       2020/5/3 09:18
 * description:  已审核的书籍
 **/
@Data
public class ReviewedVO {
    private Integer id;
    private String username;
    private String bookname;
    private String bookauthor;
    private String bookPress;
    private String shortIntro;
    private Date pressTime;
    private Integer reResult;
    private String exName;
    private String reOpinion;
    private String reReason;
    private Integer reBookid;
    private Integer bookreadnum;
    private Integer reReaderid;
}
