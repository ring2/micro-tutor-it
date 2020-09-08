package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "topic")
public class Topic {
    /**
     * 题型编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 题型名称
     */
    @Column(name = "topic_name")
    private String topicName;
}