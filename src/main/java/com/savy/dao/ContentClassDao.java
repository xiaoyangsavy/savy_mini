package com.savy.dao;

import com.savy.model.ContentClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内容分类
 */
public interface ContentClassDao {


    //查询全部内容分类，根据条件类别编号
    List<ContentClass> selectAllContentClassByTypeId(@Param("contentTypeId") Integer contentTypeId);

    //查询全部内容分类
    List<ContentClass> selectAllContentClass();
}
