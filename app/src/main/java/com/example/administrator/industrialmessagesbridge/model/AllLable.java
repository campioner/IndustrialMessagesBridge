package com.example.administrator.industrialmessagesbridge.model;

import java.util.List;

public class AllLable {
   private Integer label_id;
   private String label_name;
   private String label_frequency;
   private String  label_time;
   List<LabelTwoAll>labelTwoAllList;
   private Boolean isClick;
    public AllLable(Integer label_id, String label_name, String label_frequency, String label_time) {
        this.label_id = label_id;
        this.label_name = label_name;
        this.label_frequency = label_frequency;
        this.label_time = label_time;
    }

    public class LabelTwoAll {
      public Integer second_label_id;
      public String second_label_name;
      public String second_label_time;
      public String second_label_frequency;
        public Boolean isTwoClick;
        public LabelTwoAll(Integer second_label_id, String second_label_name, String second_label_time, String second_label_frequency) {
            this.second_label_id = second_label_id;
            this.second_label_name = second_label_name;
            this.second_label_time = second_label_time;
            this.second_label_frequency = second_label_frequency;
        }
    }

    public Boolean getClick() {
        return isClick;
    }

    public void setClick(Boolean click) {
        isClick = click;
    }

    public LabelTwoAll getALableToAll(Integer l, String r, String rr, String rrr){
        return new LabelTwoAll(l,r,rr,rrr);
    }
    public Integer getLabel_id() {
        return label_id;
    }

    public void setLabel_id(Integer label_id) {
        this.label_id = label_id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getLabel_frequency() {
        return label_frequency;
    }

    public void setLabel_frequency(String label_frequency) {
        this.label_frequency = label_frequency;
    }

    public String getLabel_time() {
        return label_time;
    }

    public void setLabel_time(String label_time) {
        this.label_time = label_time;
    }

    public List<LabelTwoAll> getLabelTwoAllList() {
        return labelTwoAllList;
    }

    public void setLabelTwoAllList(List<LabelTwoAll> labelTwoAllList) {
        this.labelTwoAllList = labelTwoAllList;
    }
}
