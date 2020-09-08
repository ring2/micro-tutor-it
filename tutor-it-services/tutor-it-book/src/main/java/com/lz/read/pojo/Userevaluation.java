package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "userevaluation")
public class Userevaluation {
    /**
     * 评价表id
     */
    @Id
    @Column(name = "ueid")
    private Integer ueid;

    /**
     * 书的id
     */
    @Column(name = "bookid")
    private Integer bookid;

    /**
     * 评价详情
     */
    @Column(name = "evaluationdetails")
    private String evaluationdetails;

    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Integer uid;

    /**
     * 评分
     */
    @Column(name = "grade")
    private Integer grade;

    /**
     * 创建时间
     */
    @Column(name = "ue_cretime")
    private Date ueCretime;
}