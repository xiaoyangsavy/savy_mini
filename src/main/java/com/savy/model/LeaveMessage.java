package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 留言表
 */
public class LeaveMessage {

    private int leaveMessageId;             //留言编号
    private String nickname;                //留言人昵称
    private String email;                   //留言人邮箱
    private String leaveMessageContent;     //留言内容
    //需要指定时区为东八区，不然相差时差8小时
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
    private Date leaveMessageSubmitDate;   //留言发布时间


    public int getLeaveMessageId() {
        return leaveMessageId;
    }

    public void setLeaveMessageId(int leaveMessageId) {
        this.leaveMessageId = leaveMessageId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLeaveMessageContent() {
        return leaveMessageContent;
    }

    public void setLeaveMessageContent(String leaveMessageContent) {
        this.leaveMessageContent = leaveMessageContent;
    }

    public Date getLeaveMessageSubmitDate() {
        return leaveMessageSubmitDate;
    }

    public void setLeaveMessageSubmitDate(Date leaveMessageSubmitDate) {
        this.leaveMessageSubmitDate = leaveMessageSubmitDate;
    }
}
