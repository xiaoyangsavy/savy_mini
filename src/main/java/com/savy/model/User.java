package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用户
 */
public class User {

    private int userId;             //用户编号
    private String userName;        //用户名
    private String password;        //密码
    private int permissionId;       //权限
    //需要指定时区为东八区，不然相差时差8小时
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
    private Date lastLoginDate;   //最后登录日期

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
