package com.example.administrator.industrialmessagesbridge.model;

public class LabelTwo {
    private Integer label_two_id;//二级标签编号
    private String labelName;//标签名
    private String labelTime;//标签时间
    private String label_frequency;//频率

    public LabelTwo(Integer label_two_id, String labelName, String labelTime, String label_frequency) {
        this.label_two_id = label_two_id;
        this.labelName = labelName;
        this.labelTime = labelTime;
        this.label_frequency = label_frequency;
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

    public String getLabel_frequency() {
        return label_frequency;
    }

    public void setLabel_frequency(String label_frequency) {
        this.label_frequency = label_frequency;
    }
}
