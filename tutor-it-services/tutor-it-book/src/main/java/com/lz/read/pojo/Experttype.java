package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "experttype")
public class Experttype {
    /**
     * 专家类别编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;


    /**
     * 专家类别名称
     */
    @Column(name = "et_name")
    private String etName;
}