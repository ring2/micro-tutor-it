package xyz.ring2.admin.core.service;

import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;
import xyz.ring2.admin.common.MinioInstance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author :     ring2
 * @date :       2020/5/11 19:54
 * description:
 **/
@Slf4j
@Component
public class MinioStorageService {

    public String uploadFile(File file1) throws Exception {
        FileInputStream file = new FileInputStream(file1);
        MinioClient minioClient = MinioInstance.getMinioClient();
        minioClient.putObject("images", "11.png", file, "image/png");
        return "success";
    }

    public boolean removeFile() {
        MinioClient minioClient = MinioInstance.getMinioClient();
        try {
            minioClient.removeObject("blog","11.png");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}
