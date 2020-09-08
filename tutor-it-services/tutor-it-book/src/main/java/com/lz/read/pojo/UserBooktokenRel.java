package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "user_booktoken_rel")
public class UserBooktokenRel {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 书卷id
     */
    @Column(name = "book_token_id")
    private Integer bookTokenId;
}