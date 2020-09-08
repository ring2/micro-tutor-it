package xyz.ring2.admin.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ring2
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("telephone")
    private String telephone;

    @TableField("credit")
    private Integer credit;

    @TableField("avatar")
    private String avatar;

    /**
     * 学习时常，单位分钟
     */
    @TableField("study_time")
    private Integer studyTime;

    @TableField("balance")
    private BigDecimal balance;

    @TableField("sex")
    private Boolean sex;

    @TableField("signature")
    private String signature;

    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
