package com.savy.controller;


import com.savy.model.ContentClass;
import com.savy.model.ContentType;
import com.savy.service.ContentService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

//博客模块
@RequestMapping(value = "/content")
@Controller
public class ContentController {

    @Autowired
    ContentService contentService;

    private static final Logger logger = Logger.getLogger(ContentController.class);

    /**
     * 上传内容
     * @param myMap
     * @return
     */
    @RequestMapping(value = "/add",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)  //以json格式返回
    @ResponseBody
    public Result addContent(@RequestBody Map<String,Object> myMap) {//名称必须与在js中定义的name值一致
        Result result=new Result();
        boolean flag = false;

        int contentTypeId=(Integer)myMap.get("contentTypeId");
        int contentClassId=(Integer)myMap.get("contentClassId");
        String title=String.valueOf(myMap.get("title"));
        String content=String.valueOf(myMap.get("content"));
        int userId=(Integer) myMap.get("userId");

        logger.info("/content/add 接口接收到的参数为：");
        logger.info("contentTypeId："+contentTypeId+"contentClassId："+contentClassId+"title:"+title+"content:"+content+"userId:"+userId);

        flag = contentService.insertContent(contentTypeId,contentClassId,title,content,userId); //调用插入方法

        if(flag){
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("添加信息成功！");
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加信息失败！");
        }
        return result;
    }


    /**
     * 获取所有内容类别
     * @return  所有内容类别
     */
    @RequestMapping(value = "/getType",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<List<ContentType>> getContentType() {
        List<ContentType> resultList = contentService.selectAllContentType();
        return Result.newSuccessResult(resultList);
    }

    /**
     * 获取所有内容分类
     * @return  所有内容分类
     */
    @RequestMapping(value = "/getClass",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<List<ContentClass>> getContentClass(int contentTypeId) {
        List<ContentClass> resultList = contentService.selectAllContentClass(contentTypeId);
        return Result.newSuccessResult(resultList);
    }
}
