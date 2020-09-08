package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "child")
public class Child {
    /**
     * 儿童表的id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 父id（用户）
     */
    @Column(name = "parentid")
    private Integer parentid;

    /**
     * 儿童的密码
     */
    @Column(name = "childpassword")
    private String childpassword;

    /**
     * 开始时间
     */
    @Column(name = "starttime")
    private Date starttime;

    /**
     * 结束时间
     */
    @Column(name = "endtime")
    private Date endtime;

    /**
     * 图书类型
     */
    @Column(name = "booktype")
    private String booktype;
}