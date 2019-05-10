package com.postengine.core;


import com.postengine.common.SqlFlow;
import com.wangjiaming.expansion.readProperties.PropertiesUtils;

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

   public static final String PROPERTIESFILENAME="sqlflow";

   public List<SqlFlow> analysis(){
       String quenu= PropertiesUtils.getProperties(PROPERTIESFILENAME,"flow.post.queue");
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
               sqlFlow.setUrl(PropertiesUtils.getProperties(PROPERTIESFILENAME,"flow.post.url."+quenuSz[i]));
               sqlFlow.setSql(PropertiesUtils.getProperties(PROPERTIESFILENAME,"flow.post.sql."+quenuSz[i]));
               sqlFlow.setConstant(PropertiesUtils.getProperties(PROPERTIESFILENAME,"flow.post.constant."+quenuSz[i]));
               sqlFlow.setReplaceStr(PropertiesUtils.getProperties(PROPERTIESFILENAME,"flow.post.replace."+quenuSz[i]));
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
