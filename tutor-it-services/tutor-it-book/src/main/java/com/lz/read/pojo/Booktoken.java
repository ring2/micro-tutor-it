package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "booktoken")
public class Booktoken {
    /**
     * 书券编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 书券名称
     */
    @Column(name = "btn_name")
    private String btnName;


    /**
     * 兑换所需积分
     */
    @Column(name = "btn_credit")
    private Integer btnCredit;

    /**
     * 书券状态：0—可用；1—不可用
     */
    @Column(name = "btn_status")
    private Byte btnStatus;

    /**
     * 书券有效期
     */
    @Column(name = "btn_time")
    private Date btnTime;

    /**
     * 书券兑换日期
     */
    @Column(name = "btn_begin")
    private Date btnBegin;

    /**
     * 书券数量
     */
    @Column(name = "btn_num")
    private Integer btnNum;

    /**
     * 书券说明
     */
    @Column(name = "btn_explain")
    private String btnExplain;
}