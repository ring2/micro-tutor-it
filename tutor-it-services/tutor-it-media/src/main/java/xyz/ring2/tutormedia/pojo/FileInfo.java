package xyz.ring2.tutormedia.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author :     weiquanquan
 * @date :       2020/3/9 20:28
 * description:
 **/
@Data
@Document(collection = "fileInfo")
public class FileInfo implements Serializable {
    @Id
    private String id;

    // 原文件名
    private String filename;

    // 文件唯一标识
    private String identifier;

    //上传时间
    private LocalDateTime uploadTime;

    // 文件总字节大小
    private Long totalSize;

    // 文件类型
    private String fileType;

    //文件MIME类型
    private String mimeType;

    // 文件存放的目录位置，包括文件名
    private String location;

    // 文件编码处理状态 已处理、未处理
    private String processStatus;
}
