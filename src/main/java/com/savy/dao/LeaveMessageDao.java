package com.savy.dao;

import com.savy.model.Content;
import com.savy.model.LeaveMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveMessageDao {

    //新增内容
    int insertLeaveMessage(LeaveMessage leaveMessage);
    //查询全部内容，根据条件
    List<LeaveMessage> selectAllLeaveMessage(@Param("currentPage") int currentPage, @Param("limitSize") int limitSize);
    //查询内容数目
    int selectLeaveMessageAmount();
}
