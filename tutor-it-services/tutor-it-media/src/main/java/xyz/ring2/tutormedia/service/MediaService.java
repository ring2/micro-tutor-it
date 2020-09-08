package xyz.ring2.tutormedia.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import xyz.ring2.tutormedia.dao.FileInfoRepository;
import xyz.ring2.tutormedia.pojo.Chunk;
import xyz.ring2.tutormedia.pojo.FileInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :     weiquanquan
 * @date :       2020/3/11 09:56
 * description:  大文件分块上传
 **/
@Service
@Slf4j
public class MediaService {

    @Value("${media.uploader.path}")
    private String filePath;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    /**
     * 校验分块是否已存在
     *
     * @param chunk
     */
    public ResponseEntity checkChunk(Chunk chunk) {
        // 先判断目录是否存在
        File folder = new File(filePath + chunk.getIdentifier());
        System.out.println(folder.getAbsolutePath());
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdir();
            return new ResponseEntity(HttpStatus.IM_USED);
        }
        // 判断该分块文件是否存在
        File chunkFile = new File(folder.getAbsolutePath() + "\\" + chunk.getChunkNumber());
        if (chunkFile.exists()) {
            // 存在则返回
            System.out.println("存在");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            System.out.println("分块不存在");
            return new ResponseEntity(HttpStatus.IM_USED);
        }
    }

    /**
     * 保存分块文件
     *
     * @param chunk
     */
    public String saveChunk(Chunk chunk) {
        MultipartFile file = chunk.getFile();
        try {
            String fileFinalPath = filePath + chunk.getIdentifier() + "\\" + chunk.getChunkNumber();
            File out = new File(fileFinalPath);

            if (!out.exists()) {
                out.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(out);
            FileCopyUtils.copy(file.getInputStream(), fileOutputStream);
        } catch (Exception e) {
            log.error("保存文件失败！！");
        }
        return "success";
    }

    /**
     * 合并分块文件
     *
     * @param chunk
     */
    public String mergeChunk(Chunk chunk) throws Exception {
        FileInfo fileInfo = new FileInfo();
        String fileFolder = filePath + chunk.getIdentifier();
        File folder = new File(fileFolder);
        List<File> fileList = new ArrayList<>(Arrays.asList(folder.listFiles()));
        fileList.sort((f1, f2) -> {
            return Integer.parseInt(f1.getName()) - Integer.parseInt(f2.getName());
        });
        //获取文件名后缀
        String fileName = chunk.getFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileInfo.setFilename(fileName);
        // 最终合并到的文件路径及名称
        String fileFinalPath = fileFolder + "\\" + chunk.getIdentifier() + suffix;
        File finalFile = new File(fileFinalPath);
        fileInfo.setLocation(fileFinalPath);
        if (!finalFile.exists()) {
            finalFile.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(finalFile, "rw");
        byte[] bytes = new byte[2048];
        // 合并文件
        for (File file : fileList) {
            RandomAccessFile randomFile = new RandomAccessFile(file, "rw");
            int len = -1;
            while ((len = randomFile.read(bytes)) != -1) {
                randomAccessFile.write(bytes, 0, len);
            }
            randomFile.close();
        }
        randomAccessFile.close();
        fileInfo.setMimeType(chunk.getType());

        //通过多线程异步处理视频编码
        // TODO
        // 保存文件信息到mongodb数据库中
        FileInfo save = fileInfoRepository.save(fileInfo);
        return "success";
    }
}
