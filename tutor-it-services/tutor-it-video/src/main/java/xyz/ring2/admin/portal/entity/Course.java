package xyz.ring2.admin.portal.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;

    /**
     * 课程介绍
     */
    @TableField("course_intro")
    private String courseIntro;

    /**
     * 课程分类
     */
    @TableField("course_cate_id")
    private Integer courseCateId;

    /**
     * 课程背景
     */
    @TableField("course_bg")
    private String courseBg;

    /**
     * 课程封面图片
     */
    @TableField("course_img")
    private String courseImg;

    /**
     * 是否收费
     */
    @TableField("is_charge")
    private Boolean isCharge;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 是否上架
     */
    @TableField("is_shelves")
    private Boolean isShelves;

    /**
     * 老师id
     */
    @TableField("lecturer_id")
    private Integer lecturerId;

    /**
     * 推广佣金
     */
    @TableField("recommend_price")
    private BigDecimal recommendPrice;

    /**
     * 购买人数
     */
    @TableField("purchaser")
    private Integer purchaser;

    /**
     * 订阅须知
     */
    @TableField("note")
    private String note;

    /**
     * 课程目标
     */
    @TableField("course_goal")
    private String courseGoal;

    /**
     * 优惠id
     */
    @TableField("discount_id")
    private Integer discountId;

    /**
     * 难度等级：0:初级
1：中级
2：高级
     */
    @TableField("difficulty_level")
    private Integer difficultyLevel;

    /**
     * 观看人数
     */
    @TableField("viewers")
    private Integer viewers;

    /**
     * 0:文字 ，1：视频
     */
    @TableField("resource_type")
    private Boolean resourceType;

    /**
     * 发布时间
     */
    @TableField("publish_time")
    private LocalDateTime publishTime;

    /**
     * 是否已删除,是：1  否：0
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    /**
     * 乐观锁
     */
    @TableField("version")
    @Version
    private Integer version;

    /**
     * 联系方式
     */
    @TableField("contact")
    private String contact;


}
