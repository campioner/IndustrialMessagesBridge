package com.example.administrator.industrialmessagesbridge.model;

public class AppointMasterDetail {
    private Integer orderId;//订单id
    private String masterName;//什么领域的专家
    private String masterImage;//专家照片
    private String orderTime;//预约时间
    private Integer orderStatue;//预约状态
    private Integer orderPrice;//预约价格
    private String masterTrueName;//专家真实姓名

    public AppointMasterDetail(Integer orderId, String masterName, String masterImage, String orderTime, Integer orderStatue, Integer orderPrice, String masterTrueName) {
        this.orderId = orderId;
        this.masterName = masterName;
        this.masterImage = masterImage;
        this.orderTime = orderTime;
        this.orderStatue = orderStatue;
        this.orderPrice = orderPrice;
        this.masterTrueName = masterTrueName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
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

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getMasterTrueName() {
        return masterTrueName;
    }

    public void setMasterTrueName(String masterTrueName) {
        this.masterTrueName = masterTrueName;
    }
}
