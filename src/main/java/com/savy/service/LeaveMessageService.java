package com.savy.service;

import com.savy.dao.LeaveMessageDao;
import com.savy.model.LeaveMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMessageService {

    @Autowired
    LeaveMessageDao leaveMessageDao;


    //新增内容
    public boolean insertLeaveMessage(LeaveMessage leaveMessage){
        boolean flag = false;
        int result = 0;
        try {
            result = leaveMessageDao.insertLeaveMessage(leaveMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result>=1){  //0表示操作失败
            flag = true;
        }
        return flag;
    }

    //查询全部内容，根据条件
    public  List<LeaveMessage> selectAllLeaveMessage(@Param("currentPage") int currentPage, @Param("limitSize") int limitSize){
        List<LeaveMessage> contentList = null;
        contentList = leaveMessageDao.selectAllLeaveMessage(currentPage,limitSize);
        return contentList;
    }
}
