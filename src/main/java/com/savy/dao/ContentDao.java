package com.savy.dao;

import com.savy.model.Content;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内容
 */
public interface ContentDao {

    //新增内容
    int insertContent(@Param("contentTypeId") int contentTypeId, @Param("contentClassId") int contentClassId, @Param("title") String title, @Param("content") String content, @Param("userId") int userId);
    //修改内容
    int updateContent(@Param("contentId") int contentId,@Param("contentTypeId") int contentTypeId, @Param("contentClassId") int contentClassId, @Param("title") String title, @Param("content") String content, @Param("userId") int userId);
    //删除内容
    int deleteContent(@Param("contentId") int contentId);
    //查询单个内容
    Content selectContentById(@Param("contentId") int contentId);
    //查询全部内容，根据条件
    List<Content> selectAllContent(@Param("currentPage") int currentPage,@Param("limitSize") int limitSize, @Param("contentTypeId") Integer contentTypeId, @Param("contentClassId") Integer contentClassId, @Param("keyword") String keyword);
    //查询内容数目
    int selectContentAmount( @Param("contentTypeId") int contentTypeId, @Param("contentClassId") int contentClassId, @Param("keyword") String keyword);

}
