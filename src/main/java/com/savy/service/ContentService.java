package com.savy.service;

import com.savy.dao.ContentClassDao;
import com.savy.dao.ContentDao;
import com.savy.dao.ContentTypeDao;
import com.savy.model.Content;
import com.savy.model.ContentClass;
import com.savy.model.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Content> selectAllContent(int currentPage, int limitSize, Date startDate, Date endDate, Integer contentTypeId, Integer contentClassId, String keyword){
        List<Content> contentList = null;
//        try {
        contentList = contentDao.selectAllContent(currentPage,limitSize,startDate,endDate,contentTypeId,contentClassId,keyword);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
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

    //查询内容分类，根据内容类别编号
    public List<ContentClass> selectAllContentClassByTypeId(Integer contentTypeId){
        List<ContentClass> contentClassList = null;
                    try {
        contentClassList = contentClassDao.selectAllContentClassByTypeId(contentTypeId);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
        return contentClassList;
    }

    //查询全部内容分类，并重构数据
    public List<Map<String,Object>> selectAllContentClass(){
        List<ContentClass> contentClassList = null;
        try {
            contentClassList = contentClassDao.selectAllContentClass();
        }catch (Exception e){
            e.printStackTrace();
        }

       List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();

        //将内容分类
        Iterator<ContentClass> allClassIterable = contentClassList.iterator();
        while(allClassIterable.hasNext()){
            boolean isExitType = false; //判断类别是否已创建
            ContentClass contentClass =  allClassIterable.next();
            System.out.println("遍历分类："+contentClass.getContentClassId());
            Map<String,Object> typeMap = null;
            List<Map<String,Object>> classList = null;
            //查看是否已存在此typeId
            Iterator<Map<String,Object>> typeIterable = resultList.iterator();
            while(typeIterable.hasNext()){
                typeMap = typeIterable.next();
                int typeId = Integer.parseInt(typeMap.get("contentTypeId").toString());
                if(typeId==contentClass.getContentTypeId()){//找到对应的typeId
                    isExitType = true;
                    System.out.println("分配到已创建的type类别中："+typeId);
                    break;
                }
            }
            if(isExitType){//已存在此typeId
                classList = (ArrayList<Map<String,Object>>)typeMap.get("data");
            }else{//不存在
                System.out.println("新建此type类别："+contentClass.getContentTypeId());
               typeMap = new HashMap<String,Object>();
                typeMap.put("contentTypeId",contentClass.getContentTypeId());
                typeMap.put("contentTypeName",contentClass.getContentTypeName());
                classList = new ArrayList<Map<String,Object>>();
                typeMap.put("data",classList);
                resultList.add(typeMap);
            }
            //将class数组整合进来
            Map<String,Object> classMap = new HashMap<String,Object>();
            classMap.put("contentClassId",contentClass.getContentClassId());
            classMap.put("contentClassName",contentClass.getContentClassName());
            classList.add(classMap);
        }
        System.out.println("数据总数为："+resultList.size());
        return resultList;
    }
}
