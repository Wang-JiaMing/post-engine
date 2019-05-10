package com.postengine.jobs;

import com.postengine.core.EngineCore;
import org.springframework.beans.factory.annotation.Autowired;
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
@PropertySource("classpath:sqlflow.properties")
public class RunEngine {

    @Autowired
    EngineCore engineCore;

    //每隔2秒执行一次
    @Scheduled(cron = "${flow.port.jsontime}")
    public void testTasks() {
        engineCore.core();
    }

}
