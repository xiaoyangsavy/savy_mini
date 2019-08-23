package com.savy.service;

import ch.qos.logback.core.encoder.EchoEncoder;
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
    public boolean insertContent(int contentTypeId, int contentClassId, String title, String content,  String overview, int userId){
        boolean flag = false;
        int result = 0;
        try {
            result = contentDao.insertContent(contentTypeId, contentClassId, title, content, overview,userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result>=1){  //0表示操作失败
            flag = true;
        }
        return flag;
    }
    //修改内容
    public boolean updateContent(int contentId,int contentTypeId,int contentClassId,String title,String content,String overview){
        boolean flag = false;
        int result = contentDao.updateContent(contentId,contentTypeId,contentClassId,title,content,overview);
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
        try {
            content = contentDao.selectContentById(contentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  content;
    }

    //查询全部内容，根据条件
    public List<Content> selectAllContent(int currentPage, int limitSize,  Integer contentTypeId, Integer contentClassId,  String keyword){
        List<Content> contentList = null;
        try {
        contentList = contentDao.selectAllContent(currentPage,limitSize,contentTypeId,contentClassId,keyword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return contentList;
    }

    //查询内容数目
    public int selectContentAmount(Integer contentTypeId, Integer contentClassId,String keyword){
        int contentAmount = 0;
        try {
        contentAmount = contentDao.selectContentAmount(contentTypeId,contentClassId,keyword);
            }catch (Exception e){
                e.printStackTrace();
            }
        return contentAmount;
    }

    //查询全部内容类别
    public List<ContentType> selectAllContentType(){
        List<ContentType> contentTypeList = null;
        try {
        contentTypeList = contentTypeDao.selectAllContentType();
                }catch (Exception e){
                    e.printStackTrace();
                }
        return contentTypeList;
    }

    //查询全部内容分类，根据内容类别编号
    public List<ContentClass> selectAllContentClass(int contentTypeId){
        List<ContentClass> contentClassList = null;
                    try {
        contentClassList = contentClassDao.selectAllContentClass(contentTypeId);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
        return contentClassList;
    }
}
