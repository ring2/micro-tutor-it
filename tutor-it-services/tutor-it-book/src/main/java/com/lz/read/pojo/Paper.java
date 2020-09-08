package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "paper")
public class Paper {
    /**
     * 问卷编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 问卷标题
     */
    @Column(name = "pa_title")
    private String paTitle;

    /**
     * 问卷副标题
     */
    @Column(name = "pa_futitle")
    private String paFutitle;

    /**
     * 问卷说明，描述
     */
    @Column(name = "pa_direction")
    private String paDirection;

    /**
     * 添加问卷日期
     */
    @Column(name = "pa_adddate")
    private Date paAdddate;

    /**
     * 操作开始日期
     */
    @Column(name = "pa_operatedate")
    private Date paOperatedate;

    /**
     * 结束问卷日期
     */
    @Column(name = "pa_enddate")
    private Date paEnddate;

    /**
     * 感谢信息
     */
    @Column(name = "pa_thankmsg")
    private String paThankmsg;
}