package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.ring2.admin.TutorVideoApplication;
import xyz.ring2.admin.core.service.MinioStorageService;

import java.io.File;

/**
 * @author :     ring2
 * @date :       2020/5/11 12:07
 * description:
 **/
@SpringBootTest(classes = {TutorVideoApplication.class})
@RunWith(SpringRunner.class)
public class ServiceTest {


    @Autowired
    MinioStorageService minioStorageService;


    @Test
    public void testInsertId() {

    }

    @Test
    public void testVersion() {
        // 更新时需要获取version值才会进行判断

    }

    // 测试上传
    @Test
    public void testUpload() throws Exception {
        File file = new File("C:\\Users\\Administrator\\Desktop\\毕业设计\\毕设相关图片\\11.png");
        System.out.println(minioStorageService.uploadFile(file));
    }
}
