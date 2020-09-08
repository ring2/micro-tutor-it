package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "wx_user")
public class WxUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户openid，当前公众号用户唯一标识
     */
    @Column(name = "openid")
    private String openid;

    /**
     * 关注状态，0取消，1订阅
     */
    @Column(name = "subscribe")
    private Integer subscribe;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 性别，1是男，2是女，0是未知
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 国家
     */
    @Column(name = "country")
    private String country;

    /**
     * 省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 语言
     */
    @Column(name = "`language`")
    private String language;

    /**
     * 头像地址
     */
    @Column(name = "headimgurl")
    private String headimgurl;

    /**
     * 关注时间
     */
    @Column(name = "subscribeTime")
    private Date subscribetime;

    /**
     * 多个公众号用户唯一标识
     */
    @Column(name = "unionid")
    private String unionid;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 分组
     */
    @Column(name = "groupid")
    private String groupid;
}