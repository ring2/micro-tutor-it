package com.lz.read.controller;

import cn.hutool.core.util.StrUtil;
import com.lz.read.common.RestResult;
import com.lz.read.dao.BookresourceMapper;
import com.lz.read.pojo.Bookresource;
import com.lz.read.service.BookresourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author :     lz
 * @date :       2020/4/2 08:50
 * description:  图书资源相关接口
 **/
@RestController
@RequestMapping("/bookResource")
public class BookResourceController {

    @Value("${file.url}")
    String fileContent;

    @Resource
    BookresourceMapper bookresourceMapper;

    @Autowired
    BookresourceService bookresourceService;

    @GetMapping("/list_source/{isCharge}")
    public RestResult getBookResource(@PathVariable Byte isCharge) {
        return bookresourceService.listBookResource(isCharge);
    }

    @PutMapping
    public RestResult update(Bookresource bookresource) {
        return bookresourceService.updateBookResource(bookresource);
    }

    @DeleteMapping
    public RestResult del(Integer id) {
        return bookresourceService.delBookResource(id);
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
        //http://localhost:8087/bookResource/view/?fileName=
        String url = "http://localhost:8087/static/" + newFileName;
        String ex = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 新增BookResource
        Bookresource bookresource = new Bookresource();
        bookresource.setRsName(newFileName);
        bookresource.setRsAmount((int)file.getSize());
        bookresource.setRsType(ex);
         bookresourceMapper.insertSelective(bookresource);
        Example example = new Example(Bookresource.class);
        example.createCriteria().andEqualTo("rsName",newFileName);
        List<Bookresource> bookresources = bookresourceMapper.selectByExample(example);
        Integer id = bookresources.get(0).getId();
        Map<String,Object> data = new HashMap<>();
        data.put("url",url);
        data.put("resourceId",id);
        return RestResult.success(data);
    }

    @PostMapping("/uploadImg")
    public RestResult cre1(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return RestResult.failureOfParam();
        }
        String originalFilename = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().substring(0, 10) + originalFilename;
        InputStream inputStream = file.getInputStream();
        File outFile = new File(fileContent+"\\img\\" + newFileName);
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        FileCopyUtils.copy(inputStream, fileOutputStream);
        //http://localhost:8087/bookResource/view/?fileName=
        String ex = originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = "http://localhost:8087/static/img/" + newFileName;
        Map<String,Object> data = new HashMap<>();
        data.put("url",url);
        return RestResult.success(data);
    }

    @GetMapping("/view")
    public String viewFile(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        String filePath = fileContent + fileName;
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
        StringBuilder sb = new StringBuilder();
        int len = 0;
        byte[] b = new byte[bis.available()];
        while ((len = bis.read(b)) != -1) {
            sb.append(new String(b, 0, len));
            sb.append("</br>");
        }
        return sb.toString();
    }

}
