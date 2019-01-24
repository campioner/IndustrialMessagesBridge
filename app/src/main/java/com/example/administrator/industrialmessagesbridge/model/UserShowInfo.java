package com.example.administrator.industrialmessagesbridge.model;

public class UserShowInfo {
    private Integer userId;//使用者id
    private String userName;//昵称
    private String userImage;//头像
    private Integer userAttentionCount;//关注数量
    private Integer userFansCount;//粉丝数
    private Integer userLevel;//等级
    private Integer userSeeCount;//访问数
    private String userQianMing;//签名
    private String userTrueName;//真实姓名
    private String userWorkTime;//工作时间
    private String userCompany;//公司
    private String userWorkPosition;//公司职位
    private String userPosition;//地址
    private Integer userSex;//性别
    private Integer isHideAll;//是否全部隐藏
    private Integer isHideName;//是否隐藏姓名
    private Integer isHideCompany;//是否隐藏公司
    private Integer isHideWorkPosition;//是否隐藏职位
    private Integer isHidePosition;//是否隐藏地址
    private Integer isHideFatiejilu;//发帖记录是否可见
    private String uesrEmail;//邮箱

    public UserShowInfo(Integer userId, String userName, String userImage, Integer userAttentionCount,
                        Integer userFansCount, Integer userLevel, String userQianMing, String userTrueName,
                        String userWorkTime, String userCompany, String userWorkPosition, Integer userSex,
                        Integer isHideAll, Integer isHideName, Integer isHideCompany, Integer isHideWorkPosition,
                        Integer isHidePosition, Integer isHideFatiejilu,Integer userSeeCount,String userPosition) {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.userAttentionCount = userAttentionCount;
        this.userFansCount = userFansCount;
        this.userLevel = userLevel;
        this.userQianMing = userQianMing;
        this.userTrueName = userTrueName;
        this.userWorkTime = userWorkTime;
        this.userCompany = userCompany;
        this.userWorkPosition = userWorkPosition;
        this.userSex = userSex;
        this.isHideAll = isHideAll;
        this.isHideName = isHideName;
        this.isHideCompany = isHideCompany;
        this.isHideWorkPosition = isHideWorkPosition;
        this.isHidePosition = isHidePosition;
        this.isHideFatiejilu = isHideFatiejilu;
        this.userSeeCount=userSeeCount;
        this.userPosition=userPosition;
    }

    public String getUesrEmail() {
        return uesrEmail;
    }

    public void setUesrEmail(String uesrEmail) {
        this.uesrEmail = uesrEmail;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public Integer getUserSeeCount() {
        return userSeeCount;
    }

    public void setUserSeeCount(Integer userSeeCount) {
        this.userSeeCount = userSeeCount;
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

    public Integer getUserAttentionCount() {
        return userAttentionCount;
    }

    public void setUserAttentionCount(Integer userAttentionCount) {
        this.userAttentionCount = userAttentionCount;
    }

    public Integer getUserFansCount() {
        return userFansCount;
    }

    public void setUserFansCount(Integer userFansCount) {
        this.userFansCount = userFansCount;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserQianMing() {
        return userQianMing;
    }

    public void setUserQianMing(String userQianMing) {
        this.userQianMing = userQianMing;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public String getUserWorkTime() {
        return userWorkTime;
    }

    public void setUserWorkTime(String userWorkTime) {
        this.userWorkTime = userWorkTime;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public String getUserWorkPosition() {
        return userWorkPosition;
    }

    public void setUserWorkPosition(String userWorkPosition) {
        this.userWorkPosition = userWorkPosition;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getIsHideAll() {
        return isHideAll;
    }

    public void setIsHideAll(Integer isHideAll) {
        this.isHideAll = isHideAll;
    }

    public Integer getIsHideName() {
        return isHideName;
    }

    public void setIsHideName(Integer isHideName) {
        this.isHideName = isHideName;
    }

    public Integer getIsHideCompany() {
        return isHideCompany;
    }

    public void setIsHideCompany(Integer isHideCompany) {
        this.isHideCompany = isHideCompany;
    }

    public Integer getIsHideWorkPosition() {
        return isHideWorkPosition;
    }

    public void setIsHideWorkPosition(Integer isHideWorkPosition) {
        this.isHideWorkPosition = isHideWorkPosition;
    }

    public Integer getIsHidePosition() {
        return isHidePosition;
    }

    public void setIsHidePosition(Integer isHidePosition) {
        this.isHidePosition = isHidePosition;
    }

    public Integer getIsHideFatiejilu() {
        return isHideFatiejilu;
    }

    public void setIsHideFatiejilu(Integer isHideFatiejilu) {
        this.isHideFatiejilu = isHideFatiejilu;
    }
}
