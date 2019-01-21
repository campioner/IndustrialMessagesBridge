package com.example.administrator.industrialmessagesbridge.model;

public class Label {
    private Integer label_id;//标签编号
    private Integer label_two_id;//二级标签编号
    private String labelName;//标签名
    private String labelTime;//标签时间

    public Label(Integer label_id, Integer label_two_id, String labelName, String labelTime) {
        this.label_id = label_id;
        this.label_two_id = label_two_id;
        this.labelName = labelName;
        this.labelTime = labelTime;
    }

    public Integer getLabel_id() {
        return label_id;
    }

    public void setLabel_id(Integer label_id) {
        this.label_id = label_id;
    }

    public Integer getLabel_two_id() {
        return label_two_id;
    }

    public void setLabel_two_id(Integer label_two_id) {
        this.label_two_id = label_two_id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelTime() {
        return labelTime;
    }

    public void setLabelTime(String labelTime) {
        this.labelTime = labelTime;
    }
}
