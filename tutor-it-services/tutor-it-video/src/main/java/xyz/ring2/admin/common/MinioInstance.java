package xyz.ring2.admin.common;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author :     ring2
 * @date :       2020/5/13 08:37
 * description:  双重检测锁 单例化MinioClientt
 **/
//@Configuration
//@ConfigurationProperties(prefix = "minio")
public class MinioInstance implements Serializable {
    private static volatile MinioClient minioClient = null;

   // @Value("${url}")
    private static String url;

    // @Value("${bucket-name}")
    private static String bucket;

    //@Value("${access-key}")
    private static String accessKey;

    //@Value("${secret-key}")
    private static String secretKey;

    // 防止反射实例化对象
    private MinioInstance() {
        if (minioClient != null) {
            throw new RuntimeException("非法的实例化操作");
        }
    }

    public static MinioClient getMinioClient() {
        if (minioClient == null) {
            synchronized (MinioClient.class) {
                if (minioClient == null) {
                    synchronized (MinioClient.class) {
                        try {
                            minioClient = new MinioClient(url, accessKey, secretKey);
                        } catch (InvalidEndpointException | InvalidPortException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return minioClient;
    }

    // 防止反序列化对象
    public static MinioClient readResolve() {
        return minioClient;
    }
}
