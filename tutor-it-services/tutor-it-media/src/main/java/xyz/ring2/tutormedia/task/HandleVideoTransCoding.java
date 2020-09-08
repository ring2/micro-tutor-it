package xyz.ring2.tutormedia.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.ring2.tutormedia.pojo.FileInfo;
import xyz.ring2.tutormedia.util.HlsVideoUtil;
import xyz.ring2.tutormedia.util.Mp4VideoUtil;

import java.util.List;

/**
 * @author :     weiquanquan
 * @date :       2020/3/11 11:23
 * description:  通过线程，异步处理视频编码
 **/
public class HandleVideoTransCoding implements Runnable{

    //ffmpeg的路径
    private final String  ffmpeg_path = "D:\\Temp\\ffmpeg-20180227-fa0c9d6-win64-static\\bin\\ffmpeg.exe ";//ffmpeg的安装位置
    //源avi视频的路径
    // private final String video_path = "D:\\Temp\\aaa.avi";

    private FileInfo fileInfo;

    @Override
    public void run() {
        handleTransCoding(fileInfo);
    }

    public HandleVideoTransCoding(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    /**
     *  视频转码，将avi文件转换成mp4并上传到minio中
     * @param fileInfo
     * @return
     */
    public String handleTransCoding(FileInfo fileInfo){
        // 获取文件类型，如果是avi则转换为mp4文件
        String fileType = fileInfo.getFileType();
        if (!fileType.equals("avi")){
            // 目前暂时不做处理
            return "can not handle";
        }
        //  原视频路径
        String videoPath = fileInfo.getLocation();
        //转换后mp4文件的名称
        String mp4_name = "1.mp4";
        //转换后mp4文件的路径
        String mp4_path = "D:\\Temp\\";

        //创建工具类对象
        Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpeg_path, videoPath, mp4_name, mp4_path);
        //开始视频转换，成功将返回success
        String s = videoUtil.generateMp4();

        return "success";
    }
}
