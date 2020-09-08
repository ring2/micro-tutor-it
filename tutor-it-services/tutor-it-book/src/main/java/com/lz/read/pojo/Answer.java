package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "answer")
public class Answer {
    /**
     * 回答编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 读者编号
     */
    @Column(name = "ans_readerid")
    private Integer ansReaderid;

    /**
     * 问题编号
     */
    @Column(name = "ans_questionid")
    private Integer ansQuestionId;

    /**
     * 回答内容
     */
    @Column(name = "ans_content")
    private String anContent;


    /**
     * 分值
     */
    @Column(name = "ans_score")
    private String ansScore;

}