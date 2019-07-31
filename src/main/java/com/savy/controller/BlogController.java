package com.savy.controller;


import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

//博客模块
@RequestMapping(value = "/blog")
@Controller
public class BlogController {


    //上传
    @RequestMapping(value = "/new",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result up(int blogTypeId, String blogTitle, String blogContent, int userId ) {//名称必须与在js中定义的name值一致
        Result result=new Result();
        boolean flag = true;



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
