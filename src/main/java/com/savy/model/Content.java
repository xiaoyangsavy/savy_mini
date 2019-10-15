package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 内容
 */
public class Content {

    private int contentId;             //内容编号
    private Integer contentTypeId;         //内容类型编号
    private Integer contentClassId;         //内容分类编号
    private String title;       //内容标题
    private String content;     //内容主体
    private String overview;    //内容概述
    //需要指定时区为东八区，不然相差时差8小时
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
    private Date submitDate;   //内容发布时间

    private String contentTypeName;//内容类型名称

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public Integer getContentClassId() {
        return contentClassId;
    }

    public void setContentClassId(Integer contentClassId) {
        this.contentClassId = contentClassId;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }
}
