package com.example.administrator.industrialmessagesbridge.model;

public class BeAppoint {
    private Integer orderId;//订单id
    private String userName;//什么领域的专家
    private String userImage;//专家照片
    private String orderTime;//预约时间
    private Integer orderStatue;//预约状态

    public BeAppoint(Integer orderId, String userName, String userImage, String orderTime, Integer orderStatue) {
        this.orderId = orderId;
        this.userName = userName;
        this.userImage = userImage;
        this.orderTime = orderTime;
        this.orderStatue = orderStatue;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatue() {
        return orderStatue;
    }

    public void setOrderStatue(Integer orderStatue) {
        this.orderStatue = orderStatue;
    }
}
