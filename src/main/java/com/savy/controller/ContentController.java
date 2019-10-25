package com.savy.controller;


import com.savy.model.Content;
import com.savy.model.ContentClass;
import com.savy.model.ContentType;
import com.savy.service.ContentService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    @RequestMapping(value = "/editContent",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)  //以json格式返回
    @ResponseBody
    public Result editContent(@RequestBody Map<String,Object> myMap) {//名称必须与在js中定义的name值一致
        Result result=new Result();
        boolean flag = false;
        Integer contentId=(Integer)myMap.get("contentId");
        int contentTypeId=(Integer)myMap.get("contentTypeId");
        int contentClassId=(Integer)myMap.get("contentClassId");
        String title=String.valueOf(myMap.get("title"));
        String content=String.valueOf(myMap.get("content"));
        String overview=String.valueOf(myMap.get("overview"));
        System.out.println("content:"+content);
        try {
            content = URLDecoder.decode(content,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("content:"+content);

        int userId=(Integer) myMap.get("userId");

        logger.info("/content/add 接口接收到的参数为：");
        logger.info("contentTypeId："+contentTypeId+"contentClassId："+contentClassId+"title:"+title+"content:"+content+"userId:"+userId);

        flag = contentService.editContent(contentId,contentTypeId,contentClassId,title,content,overview,userId); //调用插入方法

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
     * 获取所有内容
     * @return  所有内容
     */
    @RequestMapping(value = "/getContentList",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String,Object> getContentList(HttpServletRequest request, @RequestParam(value="startDate", required=false) String startDate, @RequestParam(value="endDate", required=false) String endDate, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentClassId", required=false) String contentClassId, @RequestParam(value="keyword", required=false) String keyword){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<Content> resultList = null;
        int count = 0;
        //验证传入的数据
        int pageNumber = Integer.parseInt(request.getParameter("page")); //获取当前页码，easyui默认传到后台
        int pageSize = Integer.parseInt(request.getParameter("rows")); //获取每页显示多少行，easyui默认传到后台
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDateNew = null;
        Date endDateNew = null;
        try {
            if(startDate!=null&&!"".equals(startDate)) {
                System.out.print("startDate:"+startDate);
                startDateNew = formatter.parse(startDate);
            }
            if(endDate!=null&&!"".equals(endDate)) {
                endDateNew = formatter.parse(endDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer contentTypeIdNew = null;
        if(contentTypeId!=null&&!"".equals(contentTypeId)) {
            contentTypeIdNew = Integer.parseInt(contentTypeId);
        }
        Integer contentClassIdNew = null;
        if(contentClassId!=null&&!"".equals(contentClassId)) {
            contentClassIdNew = Integer.parseInt(contentClassId);
        }
     try{
        count = contentService.selectContentAmount(startDateNew,endDateNew,contentTypeIdNew,contentClassIdNew,keyword);
        resultList = contentService.selectAllContent(pageNumber,pageSize,startDateNew,endDateNew,contentTypeIdNew,contentClassIdNew,keyword);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("resultList size="+resultList.size());
        System.out.println("count="+count);
        resultMap.put("rows",resultList);
        resultMap.put("total",count);
        return resultMap;
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
        System.out.println("/getClass paramter is :");
        System.out.println("contentTypeId="+contentTypeId);
        List<ContentClass> resultList = contentService.selectAllContentClassByTypeId(contentTypeId);
        System.out.println("result size="+resultList.size());
        return Result.newSuccessResult(resultList);
    }


    /**
     * 获取所有内容类别和分类
     * @return  所有内容类别和分类
     */
    @RequestMapping(value = "/getTypeAndClass",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<List<Map<String,Object>>> getContentTypeAndClass() {
        List<Map<String,Object>> resultList = contentService.selectAllContentClass();
        return Result.newSuccessResult(resultList);
    }

    /**
     * 获取内容详情
     * @return  所有内容详情
     */
    @RequestMapping(value = "/getContentDetail",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Content> getContentDetail(int contentId) {
        System.out.println("parameter is :"+contentId);
        Content result = contentService.selectContentById(contentId);
        return Result.newSuccessResult(result);
    }

    @RequestMapping(value = "/deleteContent",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Boolean> deleteContent(@RequestBody Map<String,Object> myMap) {
        System.out.println("call /message/deleteTypeName");
        Integer contentId=Integer.valueOf(myMap.get("contentId").toString());
        Result<Boolean> result=new Result<>();
        boolean flag =contentService.deleteContent(contentId);
        if(flag) {
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("删除成功！");
            result.setData(flag);
        }else{
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("删除失败！");
            result.setData(flag);
        }
        return  result;
    }
}
