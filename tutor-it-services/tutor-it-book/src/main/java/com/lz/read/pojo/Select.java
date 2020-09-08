package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`select`")
public class Select {
    /**
     * 选项编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 问题编号
     */
    @Column(name = "sel_questionid")
    private Integer selQuestionid;

    /**
     * 选项内容
     */
    @Column(name = "sel_content")
    private String selContent;
}