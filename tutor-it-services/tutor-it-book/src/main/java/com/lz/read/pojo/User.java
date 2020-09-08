package com.lz.read.pojo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "userid")
    @GeneratedValue(generator = "JDBC")
    private Integer userid;

    @Column(name = "username")
    private String username;

    @Column(name = "userpwd")
    private String userpwd;

    @Column(name = "usersex")
    private String usersex;

    @Column(name = "userage")
    private Integer userage;

    @Column(name = "phone")
    private String phone;

    /**
     * 身份证号码
     */
    @Column(name = "idcard")
    private String idcard;

    /**
     * 电子阅读id
     */
    @Column(name = "readid")
    private String readid;

    /**
     * 0 ：未删除、1已删除
     */
    @Column(name = "del")
    private String del;


}