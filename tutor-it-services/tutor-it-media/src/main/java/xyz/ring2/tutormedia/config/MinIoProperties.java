package xyz.ring2.tutormedia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio")
public class MinIoProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

}
