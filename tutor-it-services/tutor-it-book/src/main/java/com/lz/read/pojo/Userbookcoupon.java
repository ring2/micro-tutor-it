package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "userbookcoupon")
public class Userbookcoupon {
    /**
     * 用户书券id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Integer uid;

    /**
     * 书券id
     */
    @Column(name = "dcid")
    private Integer dcid;

    /**
     * 状态
     */
    @Column(name = "udc_status")
    private Integer udcStatus;
}