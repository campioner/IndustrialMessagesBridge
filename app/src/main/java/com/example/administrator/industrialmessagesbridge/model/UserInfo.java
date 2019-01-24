package com.example.administrator.industrialmessagesbridge.model;

public class UserInfo {
    private Integer userId;
    private String userName;
    private String userImage;
    private Integer isAttention;

    public UserInfo(Integer userId, String userName, String userImage, Integer isAttention) {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.isAttention = isAttention;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Integer getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }
}
