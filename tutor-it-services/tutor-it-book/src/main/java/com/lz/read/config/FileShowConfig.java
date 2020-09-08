package com.lz.read.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author :     真姐
 * @date :       2020/5/21 17:30
 * description:
 **/
@Configuration
public class FileShowConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径 work_project代表项目工程名 需要更改
        String path = "E:\\micro-tutor-it\\tutor-it-services\\tutor-it-book\\src\\main\\resources\\static\\img\\";
        String path1 = "E:\\micro-tutor-it\\tutor-it-services\\tutor-it-book\\src\\main\\resources\\static\\";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/img/**").
                    addResourceLocations("file:"+path);
            registry.addResourceHandler("/static/**").
                    addResourceLocations("file:"+path1);

        }else{//linux和mac系统 可以根据逻辑再做处理
            registry.addResourceHandler("/picture/**").
                    addResourceLocations("file:"+path);
        }
        super.addResourceHandlers(registry);
    }

}
