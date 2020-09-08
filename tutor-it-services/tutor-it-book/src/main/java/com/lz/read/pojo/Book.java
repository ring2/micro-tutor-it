package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "book")
public class Book {
    /**
     * 书的id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 书的名字
     */
    @Column(name = "bookname")
    private String bookname;

    /**
     * 书的图片路径名
     */
    @Column(name = "pictureurl")
    private String pictureurl;

    /**
     * 1 看书、2 听书
     */
    @Column(name = "bookstate")
    private String bookstate;

    /**
     * 书的作者
     */
    @Column(name = "bookauthor")
    private String bookauthor;

    /**
     * 阅读数量
     */
    @Column(name = "bookreadnum")
    private Integer bookreadnum;

    /**
     * 书的积分
     */
    @Column(name = "bookcredit")
    private Integer bookcredit;

    /**
     * 是否上架 0 ：未上架  1：上架
     */
    @Column(name = "book_shelves")
    private Integer bookShelves;

    /**
     * 图书类别id
     */
    @Column(name = "book_type_id")
    private Integer bookTypeId;

    /**
     * 图书资源地址
     */
    @Column(name = "book_url")
    private String bookUrl;

    /**
     * 是否收费
     */
    @Column(name = "is_charge")
    private Boolean isCharge;

    /**
     * 书卷id
     */
    @Column(name = "booktoken_id")
    private Integer bookTokenId;

    /**
     * 简介2
     */
/*    @Column(name = "bookIntroduction")
    private String bookIntroduction;*/

    /**
     * 出版社
     */
    @Column(name = "book_press")
    private String bookPress;

    /**
     * 出版时间
     */
    @Column(name = "press_time")
    private Date pressTime;

    /**
     * 类别名称
     */
    @Column(name = "booktype")
    private String bookType;

    /**
     * 发布时间
     */
/*    @Column(name = "createtime")
    private Date createTime;*/


    /**
     * 简介
     */
    @Column(name = "short_intro")
    private String shortIntro;

}