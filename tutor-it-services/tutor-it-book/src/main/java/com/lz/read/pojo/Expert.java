package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "expert")
public class Expert {
    /**
     * 专家编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 专家账号
     */
    @Column(name = "ex_username")
    private String exUsername;

    /**
     * 专家密码
     */
    @Column(name = "ex_pwd")
    private String exPwd;

    /**
     * 专家姓名
     */
    @Column(name = "ex_name")
    private String exName;

    /**
     * 专家电话
     */
    @Column(name = "ex_phone")
    private String exPhone;

    /**
     * 专家地址
     */
    @Column(name = "ex_addr")
    private String exAddr;

    /**
     * 专家身份证
     */
    @Column(name = "ex_identity")
    private String exIdentity;

    /**
     * 专家类别id
     */
    @Column(name = "ex_typeid")
    private Integer exTypeId;

    /**
     * 是否审核通过1：是  0：否
     */
    @Column(name = "is_review")
    private Byte isReview;

    /**
     * 擅长的图书类别ids
     */
    @Column(name = "book_types")
    private String bookTypes;

    /**
     * 工作单位
     */
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 职称
     */
    @Column(name = "job_title")
    private String jobTitle;


}