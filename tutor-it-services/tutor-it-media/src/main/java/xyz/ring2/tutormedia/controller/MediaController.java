package xyz.ring2.tutormedia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ring2.tutormedia.pojo.Chunk;
import xyz.ring2.tutormedia.service.MediaService;

/**
 * @author :     weiquanquan
 * @date :       2020/3/9 21:54
 * description:  媒资管理控制器
 **/
@RestController
@Slf4j
public class MediaController {

    @Autowired
    MediaService mediaService;

    // 上传分块文件
    @PostMapping("/uploader/chunk")
    public String saveChunk(Chunk chunk) {
        return mediaService.saveChunk(chunk);
    }

    // 测试分块文件是否已上传
    @GetMapping("/uploader/chunk")
    public ResponseEntity testChunkA(Chunk chunk) {
        return mediaService.checkChunk(chunk);
    }

    // 合并分块文件
    @PostMapping("/uploader/mergeFile")
    public String mergeFile(Chunk chunk) throws Exception {
        return mediaService.mergeChunk(chunk);
    }


}
