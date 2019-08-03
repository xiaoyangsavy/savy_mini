package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 博客
 */
public class Content {

    private int contentId;             //博客编号
    private int contentTypeId;         //博客类型编号
    private String title;       //博客标题
    private String content;     //博客内容
    //需要指定时区为东八区，不然相差时差8小时
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
    private Date submitDate;   //博文发布时间

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
}
