package xyz.ring2.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import xyz.ring2.admin.common.TutorITApplication;

/**
 * Tutor-it-video启动类入口
 * @author Ring2
 */
@TutorITApplication
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
public class TutorVideoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TutorVideoApplication.class, args);
    }
    @Override
    public void run(String... args) {
        StringBuilder startArgs = new StringBuilder();
        for (String arg : args) {
            startArgs.append(arg+" ");
        }
        log.info("服务启动成功，启动参数【{}】！",startArgs);
    }
}
