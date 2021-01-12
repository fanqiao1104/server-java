package com.sleep.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableCaching // 启用缓存功能
@EnableScheduling // 开启定时任务功能
@SpringBootApplication
@MapperScans({@MapperScan("com.sleep.server.dao"),@MapperScan("com.sleep.server.mapper")})
public class ScheduledApplication {
    private static Logger logger = LoggerFactory.getLogger(ScheduledApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
        logger.info("定时任务页面管理地址:{}", "http://localhost:8089/scheduled/task/taskList");
    }
}
