package xyz.ring2.tutormedia.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author :     weiquanquan
 * @date :       2020/3/9 20:28
 * description:
 **/
@Data
public class Chunk implements Serializable {
    @Id
    private Long id;
    /**
     * 当前文件块，从1开始
     */
    private Integer chunkNumber;
    /**
     * 分块大小
     */
    private Long chunkSize;
    /**
     * 当前分块大小
     */
    private Long currentChunkSize;
    /**
     * 总大小
     */
    private Long totalSize;
    /**
     * 文件标识
     */
    private String identifier;
    /**
     * 文件名
     */
    private String filename;
    /**
     * 相对路径
     */
    private String relativePath;
    /**
     * 总块数
     */
    private Integer totalChunks;
    /**
     * 文件MIME类型
     */
    private String type;

    @Transient
    private MultipartFile file;
}
