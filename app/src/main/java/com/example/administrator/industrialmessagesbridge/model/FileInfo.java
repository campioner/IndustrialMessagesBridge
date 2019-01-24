package com.example.administrator.industrialmessagesbridge.model;

public class FileInfo {
    private Integer filesId;
    private String filesUploadTime;
    private String filesName;
    private String filesPrice;

    public FileInfo(Integer filesId, String filesUploadTime, String filesName, String filesPrice) {
        this.filesId = filesId;
        this.filesUploadTime = filesUploadTime;
        this.filesName = filesName;

        this.filesPrice = filesPrice;
    }

    public Integer getFilesId() {
        return filesId;
    }

    public void setFilesId(Integer filesId) {
        this.filesId = filesId;
    }

    public String getFilesUploadTime() {
        return filesUploadTime;
    }

    public void setFilesUploadTime(String filesUploadTime) {
        this.filesUploadTime = filesUploadTime;
    }

    public String getFilesName() {
        return filesName;
    }

    public void setFilesName(String filesName) {
        this.filesName = filesName;
    }

    public String getFilesPrice() {
        return filesPrice;
    }

    public void setFilesPrice(String filesPrice) {
        this.filesPrice = filesPrice;
    }
}
