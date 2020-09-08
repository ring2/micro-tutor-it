package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Announcement;
import com.lz.read.pojo.Bookresource;
import com.lz.read.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author :     lz
 * @date :       2020/4/2 08:50
 * description:  公告相关接口
 **/
@RestController
@RequestMapping("/announce")
public class AnnouncementController {

    @Value("${file.url}")
    String fileContent;

    @Autowired
    AnnouncementService announcementService;

    @GetMapping("/list/{title}/{pageNum}/{pageSize}")
    public RestResult listAnnouncement(@PathVariable String title, @PathVariable int pageNum, @PathVariable int pageSize) {
        return announcementService.listAnnouncement(title, pageNum, pageSize);
    }

    @PutMapping
    public RestResult update(@RequestBody Announcement announcement) {
        return announcementService.updateAnnouncement(announcement);
    }

    @DeleteMapping("/{id}")
    public RestResult del(@PathVariable Integer id) {
        return announcementService.delAnnouncement(id);
    }

    @PostMapping
    public RestResult cre(@RequestBody Announcement announcement) {
        return announcementService.addAnnouncement(announcement);
    }

    @PostMapping("/upload")
    public RestResult cre(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return RestResult.failureOfParam();
        }
        String originalFilename = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().substring(0, 10) + originalFilename;
        InputStream inputStream = file.getInputStream();
        File outFile = new File(fileContent + newFileName);
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        FileCopyUtils.copy(inputStream, fileOutputStream);
        String url = "http://localhost:8081/bookResource/view/?fileName=" + newFileName;
        Example example = new Example(Bookresource.class);
        return RestResult.success(url);
    }
}
