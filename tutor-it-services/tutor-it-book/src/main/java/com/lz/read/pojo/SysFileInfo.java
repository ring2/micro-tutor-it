package com.lz.read.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "sys_file_info")
public class SysFileInfo {
    /**
     * 主键id
     */
    @Id
    @Column(name = "file_id")
    private String fileId;

    /**
     * 文件仓库（oss仓库）
     */
    @Column(name = "file_bucket")
    private String fileBucket;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件后缀
     */
    @Column(name = "file_suffix")
    private String fileSuffix;

    /**
     * 文件大小kb
     */
    @Column(name = "file_size_kb")
    private Long fileSizeKb;

    /**
     * 文件唯一标识id
     */
    @Column(name = "final_name")
    private String finalName;

    /**
     * 存储路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private Long createUser;

    /**
     * 修改用户
     */
    @Column(name = "update_user")
    private Long updateUser;
}