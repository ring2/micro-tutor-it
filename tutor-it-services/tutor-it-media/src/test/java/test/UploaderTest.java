package test;

import io.minio.PutObjectOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.ring2.tutormedia.TutorMediaApplication;
import xyz.ring2.tutormedia.config.MinIoUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author :     ring2
 * @date :       2020/5/15 10:30
 * description:
 **/
@SpringBootTest(classes = {TutorMediaApplication.class})
@RunWith(SpringRunner.class)
public class UploaderTest {
    @Autowired
    MinIoUtils minIoUtils;

    @Test
    public void testUploader() throws Exception {
        minIoUtils.makeBucket("media");
        File file = new File("D:\\框架阶段\\01 MyBatis3\\01 MyBatis第一天\\视频\\11.avi");
        FileInputStream fileInputStream = new FileInputStream(file);
        minIoUtils.putObject("media", "video11.avi", fileInputStream, new PutObjectOptions(fileInputStream.available(), -1));
    }

    @Test
    public void testRemove() {
        minIoUtils.removeObject("media", "test11.jpg");
    }

    @Test
    public void test() {
        minIoUtils.downloadFile("media", "1589284775233.png","D:\\360Downloads\\111.jpg");
    }
}
