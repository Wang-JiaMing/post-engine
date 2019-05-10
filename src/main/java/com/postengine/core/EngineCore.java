package com.postengine.core;

import com.alibaba.fastjson.JSON;
import com.postengine.common.RestTemplatesUtils;
import com.postengine.common.SqlFlow;
import com.postengine.dao.mapper.IDataSourcePost;
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
public class EngineCore {

    private static Logger logger = LoggerFactory.getLogger(EngineCore.class);

    @Autowired
    IDataSourcePost dataSourcePost;
    /**
     * 核心方法
     */
    public void core() {
        logger.info("-----运行接口引擎-----");
        /**
         * http请求准备工作
         */

        AnalysisSqlFlow analysisSqlFlow = new AnalysisSqlFlow();
        List<SqlFlow> sqlFlows = analysisSqlFlow.analysis();
        for (SqlFlow sqlFlow : sqlFlows) {
            if(sqlFlow.getConstant()!=null){

            }else if(sqlFlow.getSql()!=null) {
                List<Map<Object, Object>> results = dataSourcePost.dataPost(sqlFlow.getSql());
                String json = JSON.toJSONString(results);
                json=json.replaceAll("\\[\\{", "{").replaceAll("}]", "}");
                if(sqlFlow.getReplaceStr()!=null) {
                    String[] replaceStr=sqlFlow.getReplaceStr().split(";");
                    for(int i=0;i<replaceStr.length;i++){
                        String[] field=replaceStr[i].split("-");
                        json=json.replaceAll("\""+field[0]+"\"","\""+field[1]+"\"");
                    }
                }
                if(sqlFlow.getUrl()!=null) {
                    String result = RestTemplatesUtils.postJsonToUrl(sqlFlow.getUrl(), json);
                    logger.info("json参数:<"+json+"> ;接口URL:<"+sqlFlow.getUrl()+">;接口返回消息:<"+result);
                }
            }else{
                try{
                    throw new Exception("constant和sql两者必须配置其中一个");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        logger.info("-----结束接口引擎-----");

    }

}
