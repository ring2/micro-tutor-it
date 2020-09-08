package xyz.ring2.tutormedia.service;

import com.alibaba.nacos.common.util.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tutor.it.common.RestResult;
import xyz.ring2.tutormedia.config.MinIoUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author :     ring2
 * @date :       2020/5/25 06:54
 * description:
 **/
@Service
@Slf4j
public class MinioService {

    @Autowired
    MinIoUtils minIoUtils;

    private final String imageBucket = "images";

    public RestResult uploaderImage(MultipartFile file)  {
        if (!minIoUtils.bucketExists(imageBucket)){
            minIoUtils.makeBucket(imageBucket);
        }
        try {
            String fileName = UuidUtils.generateUuid().substring(0,10).toString()+file.getOriginalFilename();
            minIoUtils.putObject(imageBucket,fileName,file.getInputStream(),null);
            return RestResult.success("http://hw.ring2.xyz:9000/images/"+fileName);
        } catch (IOException e) {
            log.error("上传图片文件失败！");
            return RestResult.failure();
        }
    }
}
