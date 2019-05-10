package com.postengine.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @projectName:post-engine
 * @packageName:com.wonders.postengine.dao.mapper
 * @authorName:wangjiaming
 * @createDate:2019-05-09
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Component
@Mapper
public interface ITestMapper {

    @Select({"${value}"})
    List<Map<Object,Object>> test(String sqlString);


}
