package com.savy.model;

/**
 * 内容分类
 */
public class ContentClass {

    private int contentClassId;         //分类编号
    private String contentClassName;    //分类名称

    public int getContentClassId() {
        return contentClassId;
    }

    public void setContentClassId(int contentClassId) {
        this.contentClassId = contentClassId;
    }

    public String getContentClassName() {
        return contentClassName;
    }

    public void setContentClassName(String contentClassName) {
        this.contentClassName = contentClassName;
    }
}
