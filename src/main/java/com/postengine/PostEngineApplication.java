package com.postengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
/**
 * 启动定时任务功能
 */
@EnableScheduling
public class PostEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostEngineApplication.class, args);
    }

}
