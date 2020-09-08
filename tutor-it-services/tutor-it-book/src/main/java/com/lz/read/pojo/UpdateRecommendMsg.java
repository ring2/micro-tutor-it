package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "update_recommend_msg")
public class UpdateRecommendMsg {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 读者名
     */
    @Column(name = "username")
    private String username;

    /**
     * 专家名
     */
    @Column(name = "expert_Name")
    private String expertName;

    /**
     * 书籍类别
     */
    @Column(name = "book_type")
    private String bookType;

    /**
     * 结果
     */
    @Column(name = "result")
    private Boolean result;

    /**
     * 意见
     */
    @Column(name = "opinion")
    private String opinion;

    /**
     * 是否已经阅读过，0：false 1:true
     */
    @Column(name = "is_read")
    private Boolean isRead;
}