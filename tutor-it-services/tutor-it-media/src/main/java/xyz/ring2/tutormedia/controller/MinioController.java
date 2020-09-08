package xyz.ring2.tutormedia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tutor.it.common.RestResult;
import xyz.ring2.tutormedia.service.MinioService;

/**
 * @author :     ring2
 * @date :       2020/5/25 07:19
 * description:
 **/
@RestController
@Slf4j
@RequestMapping("/upload")
public class MinioController {

    @Autowired
    MinioService minioService;

    @PostMapping("/img")
    public RestResult uploadImage(MultipartFile file) {
        return minioService.uploaderImage(file);
    }
}
