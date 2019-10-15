package com.savy.model;

/**
 * 内容分类
 */
public class ContentClass {

    private Integer contentClassId;         //分类编号
    private String contentClassName;    //分类名称
    private Integer contentTypeId;     //类别编号
    private String contentTypeName;     //类别名称

    public Integer getContentClassId() {
        return contentClassId;
    }

    public void setContentClassId(Integer contentClassId) {
        this.contentClassId = contentClassId;
    }

    public String getContentClassName() {
        return contentClassName;
    }

    public void setContentClassName(String contentClassName) {
        this.contentClassName = contentClassName;
    }

    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }
}
