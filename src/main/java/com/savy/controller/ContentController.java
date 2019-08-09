package com.savy.controller;


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

import java.util.Map;

//博客模块
@RequestMapping(value = "/content")
@Controller
public class ContentController {

    @Autowired
    ContentService contentService;

    private static final Logger logger = Logger.getLogger(ContentController.class);
    //上传
    @RequestMapping(value = "/add",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
}
