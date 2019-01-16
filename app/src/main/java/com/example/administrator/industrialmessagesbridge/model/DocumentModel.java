package com.example.administrator.industrialmessagesbridge.model;

import java.lang.reflect.Type;

public class DocumentModel {
    private Integer documentPhoto;
    private String documentDescribe;//职位描述
    private String documentSendTime;//工作时间
    private String documentPrice;//position
    private int type;

    public DocumentModel(Integer documentPhoto, String documentDescribe, String documentSendTime, String documentPrice, int type) {
        this.documentPhoto = documentPhoto;
        this.documentDescribe = documentDescribe;
        this.documentSendTime = documentSendTime;
        this.documentPrice = documentPrice;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getDocumentPhoto() {
        return documentPhoto;
    }

    public void setDocumentPhoto(Integer documentPhoto) {
        this.documentPhoto = documentPhoto;
    }

    public String getDocumentDescribe() {
        return documentDescribe;
    }

    public void setDocumentDescribe(String documentDescribe) {
        this.documentDescribe = documentDescribe;
    }

    public String getDocumentSendTime() {
        return documentSendTime;
    }

    public void setDocumentSendTime(String documentSendTime) {
        this.documentSendTime = documentSendTime;
    }

    public String getDocumentPrice() {
        return documentPrice;
    }

    public void setDocumentPrice(String documentPrice) {
        this.documentPrice = documentPrice;
    }
}
