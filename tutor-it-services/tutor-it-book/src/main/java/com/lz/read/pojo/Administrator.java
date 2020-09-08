package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "administrator")
public class Administrator {
    /**
     * 管理员编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 管理员账号
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 管理员密码
     */
    @Column(name = "admin_pwd")
    private String adminPwd;
}