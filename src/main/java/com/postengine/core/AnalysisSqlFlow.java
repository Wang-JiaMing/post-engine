package com.postengine.core;


import com.postengine.common.InitConfig;
import com.postengine.common.SqlFlow;
import com.expansion.readProperties.PropertiesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName:post-engine
 * @packageName:com.wonders.postengine.core
 * @authorName:wangjiaming
 * @createDate:2019-05-09
 * @editor:IntelliJ IDEA
 * @other:解析配置文件
 **/
public class AnalysisSqlFlow {



   public List<SqlFlow> analysis(){
       String quenu= PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.FLOWCORE+".properties","flow.post.queue");
       if(quenu.equals("")){
           try {
               throw new Exception("sqlflow.properties的必备参数flow.post.queue缺失");
           } catch (Exception e) {
               e.printStackTrace();
           }
           return null;
       }else{
           String[] quenuSz=quenu.split(";");
           List<SqlFlow> sqlFlows=new ArrayList<>(quenuSz.length);
           for(int i=0;i<quenuSz.length;i++){
               SqlFlow sqlFlow=new SqlFlow();
               sqlFlow.setUrl(PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.FLOWCORE+".properties","flow.post.url."+quenuSz[i]));
               sqlFlow.setSql(PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.FLOWCORE+".properties","flow.post.sql."+quenuSz[i]));
               sqlFlow.setConstant(PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.FLOWCORE+".properties","flow.post.constant."+quenuSz[i]));
               sqlFlow.setReplaceStr(PropertiesUtils.getPropertiesByPath(InitConfig.DIRPATH+InitConfig.FLOWCORE+".properties","flow.post.replace."+quenuSz[i]));
               sqlFlows.add(sqlFlow);
           }
           return sqlFlows;
       }
   }

    public static void main(String[] args) {
        AnalysisSqlFlow analysisSqlFlow=new AnalysisSqlFlow();
        System.out.println(analysisSqlFlow.analysis().size());
    }
}
