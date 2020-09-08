package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "recommend")
public class Recommend {
    /**
     * 推荐图书编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 读者编号
     */
    @Column(name = "re_readerid")
    private Integer reReaderid;

    /**
     * 图书编号
     */
    @Column(name = "re_bookid")
    private Integer reBookid;

    /**
     * 审核结果
     */
    @Column(name = "re_result")
    private Byte reResult;

    /**
     * 审核的专家id
     */
    @Column(name = "re_expertid")
    private Integer reExpertId;

    /**
     * 审核状态 0:未审核  1：已审核
     */
    @Column(name = "re_status")
    private Byte reStatus;

    /**
     * 审核意见、结论
     */
    @Column(name = "re_opinion")
    private String reOpinion;

    /**
     * 推荐历有
     */
    @Column(name = "re_reason")
    private String reReason;
}