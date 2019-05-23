package com.postengine.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName:post-engine
 * @packageName:com.wonders.postengine.common
 * @authorName:wangjiaming
 * @createDate:2019-05-09
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class RestTemplatesUtils {

    public static String postJsonToUrl(String url, String jsonStr) {
//        超时
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(30000);
        httpRequestFactory.setConnectTimeout(5000);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        messageConverters.add(stringHttpMessageConverter);
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new SourceHttpMessageConverter());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.setMessageConverters(messageConverters);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        return result;
    }

    public static void main(String[] args) {
        String strJson = "{\n" +
                "\t\"loginName\":\"yxadmin\",\n" +
                "\t\"menuCode\":\"YX-HEALTHCARE\"\n" +
                "}";
        System.out.println(RestTemplatesUtils.postJsonToUrl("http://qy1.sfda.gov.cn/datasearchcnda/face3/base.jsp?tableId=25&tableName=TABLE25&title=%E5%9B%BD%E4%BA%A7%E8%8D%AF%E5%93%81&bcId=152904713761213296322795806604",null));


    }

}
