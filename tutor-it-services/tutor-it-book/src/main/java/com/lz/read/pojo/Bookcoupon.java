package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "bookcoupon")
public class Bookcoupon {
    /**
     * 书券id
     */
    @Id
    @Column(name = "dcid")
    @GeneratedValue(generator = "JDBC")
    private Integer dcid;

    /**
     * 书券标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 开始时间
     */
    @Column(name = "starttime")
    private Date starttime;

    /**
     * 截至时间
     */
    @Column(name = "endtime")
    private Date endtime;

    /**
     * 所需积分
     */
    @Column(name = "integral")
    private Integer integral;

    /**
     * 书券状态，0启用，1不可用
     */
    @Column(name = "dc_status")
    private Integer dcStatus;
}