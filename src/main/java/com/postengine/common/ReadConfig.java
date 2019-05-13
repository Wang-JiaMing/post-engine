package com.postengine.common;

import com.wangjiaming.expansion.readProperties.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @projectName:post-engine
 * @packageName:com.postengine.common
 * @authorName:wangjiaming
 * @createDate:2019-05-13
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Slf4j
@Service
public class ReadConfig {

    private static final String APPLICATIONNAME="application";


    static {
        log.info("~~~~~~~~~~~预浏览配置文件begin~~~~~~~~~~~");
        log.info("JDBC_URL:"+PropertiesUtils.getProperties(APPLICATIONNAME,"spring.datasource.druid.url"));
        log.info("JDBC_USERNAME:"+PropertiesUtils.getProperties(APPLICATIONNAME,"spring.datasource.druid.username"));
        log.info("JDBC_PASSWORD:"+PropertiesUtils.getProperties(APPLICATIONNAME,"spring.datasource.druid.password"));
        log.info("引擎队列:"+PropertiesUtils.getProperties(APPLICATIONNAME,"flow.post.queue"));
        log.info("引擎作业时间:"+PropertiesUtils.getProperties(APPLICATIONNAME,"flow.port.jsontime"));
        log.info("~~~~~~~~~~~预浏览配置文件end~~~~~~~~~~~");
    }

}
