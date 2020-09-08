package com.lz.read.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author :     ring2
 * @date :       2020/5/21 20:26
 * description:
 **/
@Data
@Table(name = "books")
public class Books {
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
     * 类别名称
     */
    @Column(name = "booktype")
    private String bookType;


    @Column(name = "createtime")
    private Date createTime;


    /*   *
     * 简介2*/
    @Column(name = "bookIntroduction")
    private String bookIntroduction;

    /**
     * 书的积分
     */
    @Column(name = "bookcredit")
    private Integer bookcredit;


}
