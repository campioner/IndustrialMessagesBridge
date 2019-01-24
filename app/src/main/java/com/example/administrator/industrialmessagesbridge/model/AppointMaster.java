package com.example.administrator.industrialmessagesbridge.model;

public class AppointMaster {
    private Integer orderId;//订单id
    private String masterName;//什么领域的专家
    private String masterImage;//专家照片
    private String orderTime;//预约时间
    private Integer orderStatue;//预约状态

    public AppointMaster(Integer orderId,String masterName, String masterImage, String orderTime, Integer orderStatue) {
        this.orderId = orderId;
       this. masterName=masterName;
        this.masterImage = masterImage;
        this.orderTime = orderTime;
        this.orderStatue = orderStatue;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMasterImage() {
        return masterImage;
    }

    public void setMasterImage(String masterImage) {
        this.masterImage = masterImage;
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
