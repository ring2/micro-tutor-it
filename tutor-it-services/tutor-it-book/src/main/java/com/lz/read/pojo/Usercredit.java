package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "usercredit")
public class Usercredit {
    /**
     * 用户积分表
     */
    @Id
    @Column(name = "ucid")
    @GeneratedValue(generator = "JDBC")
    private Integer ucid;

    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Integer uid;

    /**
     * 积分
     */
    @Column(name = "integral")
    private Integer integral;
}