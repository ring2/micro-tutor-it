package xyz.ring2.tutormedia.config;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * 工具类
 * 爪哇笔记：https://blog.52itstyle.vip
 */
@Component
@Configuration
@EnableConfigurationProperties({MinIoProperties.class})
public class MinIoUtils {

    private final MinIoProperties minIo;

    public MinIoUtils(MinIoProperties minIo) {
        this.minIo = minIo;
    }

    private MinioClient instance;

    @PostConstruct
    public void init() {
        try {
            instance = new MinioClient(minIo.getEndpoint(),minIo.getAccessKey(),minIo.getSecretKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断 bucket是否存在
     * @param bucketName 桶名称
     * @return 是否存在
     */
    public boolean bucketExists(String bucketName){
        try {
            return instance.bucketExists(bucketName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建 bucket
     * @param bucketName 桶名称
     */
    public void makeBucket(String bucketName){
        try {
            boolean isExist = instance.bucketExists(bucketName);
            if(!isExist) {
                instance.makeBucket(bucketName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param filename 文件名称
     */
    public void putObject(String bucketName, String objectName, String filename){
        try {
            instance.putObject(bucketName,objectName,filename,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 文件上传
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param stream    输入流
     */
    public void putObject(String bucketName, String objectName, InputStream stream, PutObjectOptions options){
        try {
            instance.putObject(bucketName,objectName,stream,new PutObjectOptions(stream.available(),-1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除文件
     * @param bucketName 桶名称
     * @param objectName 对象名
     */
    public void removeObject(String bucketName, String objectName){
        try {
            instance.removeObject(bucketName,objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件下载
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @param downloadPath 下载路径
     */
    public void downloadFile(String bucketName, String objectName, String downloadPath){
        File file = new File(downloadPath);
        try (OutputStream out = new FileOutputStream(file)) {
            InputStream inputStream = instance.getObject(bucketName, objectName);
            byte[] bytes = new byte[1024];
            int byteRead;
            while ((byteRead = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, byteRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 文件下载
     * @param bucketName 桶名称
     * @param objectName 对象名称
     */
    public InputStream downloadFile(String bucketName, String objectName){
        try {
            return  instance.getObject(bucketName, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取下载链接
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expiry 失效时间（以秒为单位），默认是7天，不得大于七天。
     * @return
     */
    public String presignedGetObject(String bucketName, String objectName, int expiry){
        try {
            return  instance.presignedGetObject(bucketName, objectName, expiry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
