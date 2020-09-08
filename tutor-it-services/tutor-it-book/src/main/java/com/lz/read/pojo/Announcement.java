package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "announcement")
public class Announcement {
    /**
     * 公告编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 公告标题
     */
    @Column(name = "anno_title")
    private String annoTitle;

    /**
     * 公告内容
     */
    @Column(name = "anno_content")
    private String annoContent;

    /**
     * 发布时间
     */
    @Column(name = "anno_publishtime")
    private Date annoPublishtime;

    /**
     * 是否撤销：0—否；1—是
     */
    @Column(name = "anno_cancel")
    private Byte annoCancel;

    /**
     * 撤销时间
     */
    @Column(name = "anno_canceltime")
    private Date annoCanceltime;

    /**
     * 是否删除：0—否；1—是
     */
    @Column(name = "anno_delete")
    private Byte annoDelete;

    /**
     * 删除时间
     */
    @Column(name = "anno_deletetime")
    private Date annoDeletetime;

    /**
     * 附件url
     */
    @Column(name = "attach_url")
    private String attachUrl;
}