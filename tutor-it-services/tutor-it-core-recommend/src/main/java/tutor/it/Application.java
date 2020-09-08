package tutor.it;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :     ring2
 * @date :       2020/5/26 07:12
 * description:
 **/
@SpringBootApplication
@MapperScan(value = {"tutor.it.generator.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
