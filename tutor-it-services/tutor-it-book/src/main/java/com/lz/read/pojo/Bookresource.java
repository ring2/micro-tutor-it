package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "bookresource")
public class Bookresource {
    /**
     * 图书资源编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;


    /**
     * 资源名称
     */
    @Column(name = "rs_name")
    private String rsName;

    /**
     * 资源简介
     */
    @Column(name = "rs_synopsis")
    private String rsSynopsis;

    /**
     * 资源大小，单位为KB或MB
     */
    @Column(name = "rs_amount")
    private Integer rsAmount;

    /**
     * 资源类型，类似于doc、ppt、pdf、MP3、wmv等常用格式
     */
    @Column(name = "rs_type")
    private String rsType;


    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Integer bookId;
}