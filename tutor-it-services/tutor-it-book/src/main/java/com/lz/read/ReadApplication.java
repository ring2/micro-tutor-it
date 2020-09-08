package com.lz.read;

import com.lz.read.common.ReadBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@ReadBootApplication
@EnableDiscoveryClient
public class ReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadApplication.class, args);
    }

}
