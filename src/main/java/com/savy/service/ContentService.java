package com.savy.service;

import com.savy.dao.ContentClassDao;
import com.savy.dao.ContentDao;
import com.savy.dao.ContentTypeDao;
import com.savy.model.Content;
import com.savy.model.ContentClass;
import com.savy.model.ContentType;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容
 */
@Service
public class ContentService {

    @Autowired
    ContentDao contentDao;
    @Autowired
    ContentTypeDao contentTypeDao;
    @Autowired
    ContentClassDao contentClassDao;

    //新增内容
    public boolean insertContent(int contentTypeId, int contentClassId, String title, String content, int userId){
        boolean flag = false;
        int result = 0;
        try {
            result = contentDao.insertContent(contentTypeId, contentClassId, title, content, userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result>=1){  //0表示操作失败
            flag = true;
        }
        return flag;
    }
    //修改内容
    public boolean updateContent(int contentId,int contentTypeId,int contentClassId,String title,String content,int userId){
        boolean flag = false;
        int result = contentDao.updateContent(contentId,contentTypeId,contentClassId,title,content,userId);
        if(result>=1){  //result为受影响的行，0表示操作失败
            flag = true;
        }
        return flag;
    }
    //删除内容
    public boolean deleteContent(int contentId){
        boolean flag = false;
        int result = contentDao.deleteContent(contentId);
        if(result>=1){  //result为受影响的行，0表示操作失败
            flag = true;
        }
        return flag;
    }

    //查询单个内容
    public  Content selectContentById(int contentId){
        Content content = null;
        content = contentDao.selectContentById(contentId);
        return  content;
    }

    //查询全部内容，根据条件
    public List<Content> selectAllContent(@Param("currentPage") int currentPage, @Param("limitSize") int limitSize,  @Param("contentTypeId") Integer contentTypeId, @Param("contentClassId") Integer contentClassId, @Param("keyword") String keyword){
        List<Content> contentList = contentDao.selectAllContent(currentPage,limitSize,contentTypeId,contentClassId,keyword);
        return contentList;
    }

    //查询内容数目
    public int selectContentAmount(@Param("contentTypeId") int contentTypeId, @Param("contentClassId") int contentClassId, @Param("keyword") String keyword){
        int contentAmount = 0;
        contentAmount = contentDao.selectContentAmount(contentTypeId,contentClassId,keyword);
        return contentAmount;
    }

    //查询全部内容类别
    public List<ContentType> selectAllContentType(){
        List<ContentType> contentTypeList = contentTypeDao.selectAllContentType();
        return contentTypeList;
    }

    //查询全部内容分类，根据内容类别编号
    public List<ContentClass> selectAllContentClass(int contentTypeId){
        List<ContentClass> contentClassList = contentClassDao.selectAllContentClass(contentTypeId);
        return contentClassList;
    }
}
