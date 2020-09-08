package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "usercollection")
public class Usercollection {
    /**
     * 收藏表的id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "userid")
    private Integer userid;

    /**
     * 收藏书的id
     */
    @Column(name = "bookid")
    private Integer bookid;
}