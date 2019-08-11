package com.savy.model;

/**
 * 内容类别
 */
public class ContentType {

    private int contentTypeId;          //类别编号
    private String contentTypeName;     //类别名称


    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }
}
