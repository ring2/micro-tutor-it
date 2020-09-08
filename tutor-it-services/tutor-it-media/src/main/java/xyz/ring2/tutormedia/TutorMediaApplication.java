package xyz.ring2.tutormedia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
public class TutorMediaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TutorMediaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("媒资管理子系统启动...");
    }
}
