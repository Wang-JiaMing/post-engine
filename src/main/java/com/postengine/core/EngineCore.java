package com.postengine.core;

import com.alibaba.fastjson.JSON;
import com.postengine.common.RestTemplatesUtils;
import com.postengine.common.SqlFlow;
import com.postengine.dao.mapper.IDataSourcePost;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @projectName:post-engine
 * @packageName:com.wonders.postengine.core
 * @authorName:wangjiaming
 * @createDate:2019-05-09
 * @editor:IntelliJ IDEA
 * @other:接口引擎主方法入口
 **/
@Service
@Slf4j
public class EngineCore {

    @Autowired
    IDataSourcePost dataSourcePost;

    /**
     * 核心方法
     */
    public void core() {
        /**
         * http请求准备工作
         */

        AnalysisSqlFlow analysisSqlFlow = new AnalysisSqlFlow();
        List<SqlFlow> sqlFlows = analysisSqlFlow.analysis();
        log.info("------------一共存在" + sqlFlows.size() + "个接口队列------------");
        for (SqlFlow sqlFlow : sqlFlows) {
            if (sqlFlow.getConstant() != null) {
                log.info("------------进入常量接口------------");

            } else if (sqlFlow.getSql() != null) {
                log.info("------------进入sql动态接口------------");
                List<Map<Object, Object>> results = dataSourcePost.dataPost(sqlFlow.getSql());
                String json = JSON.toJSONString(results);
                json = json.replaceAll("\\[\\{", "{").replaceAll("}]", "}");
                if (sqlFlow.getReplaceStr() != null) {
                    String[] replaceStr = sqlFlow.getReplaceStr().split(";");
                    for (int i = 0; i < replaceStr.length; i++) {
                        String[] field = replaceStr[i].split("-");
                        json = json.replaceAll("\"" + field[0] + "\"", "\"" + field[1] + "\"");
                    }
                }
                log.info("------------sql动态接口传递json:" + json + "------------");
                log.info("------------动态接口配置url为:" + sqlFlow.getUrl() + "------------");
                if (sqlFlow.getUrl() != null) {
                    String result = RestTemplatesUtils.postJsonToUrl(sqlFlow.getUrl(), json);
                    log.info("json参数:<" + json + "> ;接口URL:<" + sqlFlow.getUrl() + ">;接口返回消息:<" + result);
                }
            } else {
                try {
                    log.error("------------constant和sql两者必须配置其中一个------------");
                    throw new Exception("constant和sql两者必须配置其中一个");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
