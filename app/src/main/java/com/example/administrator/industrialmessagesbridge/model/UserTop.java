package com.example.administrator.industrialmessagesbridge.model;

public class UserTop {
    private Integer userId;
    private String userName;
    private String userImage;
    private Integer level;

    public UserTop(Integer userId, String userName, String userImage, Integer level) {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.level = level;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
