package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 博客
 */
public class Blog {

    private int blogId;             //博客编号
    private int blogTypeId;         //博客类型编号
    private String blogTitle;       //博客标题
    private String blogContent;     //博客内容
    //需要指定时区为东八区，不然相差时差8小时
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
    private Date blogSubmitDate;   //博文发布时间
    private int userId;             //用户编号

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getBlogTypeId() {
        return blogTypeId;
    }

    public void setBlogTypeId(int blogTypeId) {
        this.blogTypeId = blogTypeId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Date getBlogSubmitDate() {
        return blogSubmitDate;
    }

    public void setBlogSubmitDate(Date blogSubmitDate) {
        this.blogSubmitDate = blogSubmitDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
