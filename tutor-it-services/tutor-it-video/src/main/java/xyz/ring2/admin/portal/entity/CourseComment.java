package xyz.ring2.admin.portal.entity;

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
@TableName("course_comment")
public class CourseComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("comment_content")
    private String commentContent;

    @TableField("like_num")
    private Integer likeNum;

    @TableField("course_id")
    private Integer courseId;

    @TableField("create_date")
    private LocalDateTime createDate;

    @TableField("user_id")
    private Integer userId;

    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
