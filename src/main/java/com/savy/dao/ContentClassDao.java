package com.savy.dao;

import com.savy.model.ContentClass;

import java.util.List;

/**
 * 内容分类
 */
public interface ContentClassDao {


    //查询全部内容分类，根据条件类别编号
    List<ContentClass> selectAllContentClass(int contentTypeId);
}
