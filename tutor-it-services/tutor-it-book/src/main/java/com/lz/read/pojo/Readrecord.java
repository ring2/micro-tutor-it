package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "readrecord")
public class Readrecord {
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
     * 书的id
     */
    @Column(name = "bookid")
    private Integer bookid;
}