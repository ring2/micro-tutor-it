package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "booktype")
public class Booktype {
    /**
     * 图书类别编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 图书类别名称
     */
    @Column(name = "bt_name")
    private String btName;
}