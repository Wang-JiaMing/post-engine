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
public class ReadConfig{
    static {
        log.info("~~~~~~~~~~~预浏览外部配置文件begin~~~~~~~~~~~");
        log.info("dirPath"+InitConfig.DIRPATH);
        log.info("JDBC_URL:" + PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.APPLICATION+".properties", "spring.datasource.druid.url"));
        log.info("JDBC_USERNAME:" + PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.APPLICATION+".properties", "spring.datasource.druid.username"));
        log.info("JDBC_PASSWORD:" + PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.APPLICATION+".properties", "spring.datasource.druid.password"));
        log.info("引擎队列:" + PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.FLOWCORE+".properties", "flow.post.queue"));
        log.info("引擎作业时间:" + PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.FLOWCORE+".properties", "flow.port.jobtime"));
        log.info("~~~~~~~~~~~预浏览外部配置文件end~~~~~~~~~~~");
    }

}
