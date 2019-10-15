package com.savy.controller;

import com.savy.model.Content;
import com.savy.model.LeaveMessage;
import com.savy.service.LeaveMessageService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 留言板
 */
@RequestMapping(value = "/leaveMessage")
@Controller
public class LeaveMessageController {
    private static final Logger logger = Logger.getLogger(LeaveMessageController.class);
    @Autowired
    LeaveMessageService leaveMessageService;


    /**
     * 上传内容
     * @param myMap
     * @return
     */
    @RequestMapping(value = "/add",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)  //以json格式返回
    @ResponseBody
    public Result addLeaveMessage(@RequestBody Map<String,Object> myMap) {//名称必须与在js中定义的name值一致
        Result result=new Result();
        boolean flag = false;

        String nickname=myMap.get("nickname")!=null?myMap.get("nickname").toString():"";
        String email=myMap.get("email")!=null?myMap.get("email").toString():"";
        String leaveMessageContent=myMap.get("leaveMessageContent")!=null?myMap.get("leaveMessageContent").toString():"";


        logger.info("/content/add 接口接收到的参数为：");
        logger.info("nickname："+nickname+"email："+email+"leaveMessageContent:"+leaveMessageContent);

        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setNickname(nickname);
        leaveMessage.setEmail(email);
        leaveMessage.setLeaveMessageContent(leaveMessageContent);

        flag = leaveMessageService.insertLeaveMessage(leaveMessage); //调用插入方法

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
    @RequestMapping(value = "/getLeaveMessageList",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Map<String,Object>> getLeaveMessageList(@Param("currentPage") int currentPage, @Param("limitSize") int limitSize){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<LeaveMessage> resultList = null;
        int count = 0;
        //验证传入的数据
        try{
            resultList = leaveMessageService.selectAllLeaveMessage(currentPage,limitSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("resultList size="+resultList.size());
        resultMap.put("data",resultList);
        return Result.newSuccessResult(resultMap);
    }
}
