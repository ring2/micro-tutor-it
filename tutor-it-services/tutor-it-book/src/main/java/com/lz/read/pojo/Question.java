package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "question")
public class Question {
    /**
     * 问题编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 问题名称
     */
    @Column(name = "que_title")
    private String queTitle;

    /**
     * 问题说明
     */
    @Column(name = "que_direction")
    private String queDirection;

    /**
     * 是否必答：0—是；1—否
     */
    @Column(name = "que_answer")
    private Byte queAnswer;

    /**
     * 问题编号
     */
    @Column(name = "que_paperid")
    private Integer quePaperid;

    /**
     * 题型编号
     */
    @Column(name = "que_topicid")
    private Integer queTopicid;

    /**
     * 问题类型
     */
    @Column(name = "que_type")
    private String queType;
}