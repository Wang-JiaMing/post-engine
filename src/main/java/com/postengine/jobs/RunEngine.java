package com.postengine.jobs;

import com.postengine.core.EngineCore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @projectName:post-engine
 * @packageName:com.wonders.postengine.jobs
 * @authorName:wangjiaming
 * @createDate:2019-05-09
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Component
@Configuration
@PropertySource(value = "file:${flowcore.path}",ignoreResourceNotFound = true)
@Slf4j
public class RunEngine {

    @Autowired
    EngineCore engineCore;

    //每隔2秒执行一次
    @Scheduled(cron = "${flow.port.jobtime}")
    public void testTasks() {
        log.info("===============启动定时作业===============");
        engineCore.core();
        log.info("===============结束定时作业===============");

    }

}
