package com.lz.read.service.task;

import com.lz.read.service.impl.RecommendSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author :     ring2
 * @date :       2020/5/3 13:19
 * description: 定时任务，定时扫面数据库中新添加的未审核的推荐书籍，
 * 自动分配给相关领域的专家
 **/
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class RecommendScheduleTask {

    @Resource
    RecommendSchedule recommendSchedule;

    //3.添加定时任务 初步设置为10分钟执行一次
    @Scheduled(cron = "0 0/10 * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        log.info("开始执行定时任务。。");
       recommendSchedule.executeTask();
       log.info("定时任务执行结束");
    }
}