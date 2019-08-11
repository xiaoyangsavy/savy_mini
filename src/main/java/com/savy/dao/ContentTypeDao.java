package com.savy.dao;

import com.savy.model.ContentType;

import java.util.List;

/**
 * 内容类别
 */
public interface ContentTypeDao {


    //查询全部内容类别
    List<ContentType> selectAllContentType();

}
