package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`check`")
public class Check {
    /**
     * 图书审核编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 专家编号
     */
    @Column(name = "ck_expertid")
    private Integer ckExpertid;

    /**
     * 推荐图书编号
     */
    @Column(name = "ck_recommendid")
    private Integer ckRecommendid;

    /**
     * 审核是否通过：0—通过；1—不通过
     */
    @Column(name = "ck_pass")
    private Byte ckPass;
}