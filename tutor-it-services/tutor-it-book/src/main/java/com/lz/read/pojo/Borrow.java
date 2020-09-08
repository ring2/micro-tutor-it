package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "borrow")
public class Borrow {
    /**
     * 借阅编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 图书编号
     */
    @Column(name = "bw_bookid")
    private Integer bwBookid;

    /**
     * 读者编号
     */
    @Column(name = "bw_readerid")
    private Integer bwReaderid;

    /**
     * 借出日期
     */
    @Column(name = "bw_outtime")
    private Date bwOuttime;

    /**
     * 到期日期
     */
    @Column(name = "bw_endtime")
    private Date bwEndtime;

    /**
     * 归还日期
     */
    @Column(name = "bw_backtime")
    private Date bwBacktime;

    /**
     * 是否过期：0--不过期；1--过期
     */
    @Column(name = "bw_isexpired")
    private Byte bwIsexpired;
}