package com.example.administrator.industrialmessagesbridge.model;

public class EHui {
    private Integer topicId;//e汇id
    private Integer lableId;//二级标签id
    private String lableName;//二级标签名
    private String topicName;//话题名
    private String topicCreatetime;//创建时间
    private String topicBrief;//简介
    private String fensiCount;//粉丝数
    private String tieziCount;//帖子数
    private Integer statue;//加入状态
    private String topicImage;//图片
    private Integer level;
    public EHui(Integer topicId, Integer lableId, String lableName, String topicName, String topicOwner, String topicViceowner, String topicCreatetime, String topicBrief, String fensiCount, String tieziCount, Integer statue, String topicImage) {
        this.topicId = topicId;
        this.lableId = lableId;
        this.lableName = lableName;
        this.topicName = topicName;
        this.statue=statue;
        this.topicCreatetime = topicCreatetime;
        this.topicBrief = topicBrief;
        this.fensiCount = fensiCount;
        this.tieziCount = tieziCount;
        this.topicImage = topicImage;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public String getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(String topicImage) {
        this.topicImage = topicImage;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getLableId() {
        return lableId;
    }

    public void setLableId(Integer lableId) {
        this.lableId = lableId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }



    public String getTopicCreatetime() {
        return topicCreatetime;
    }

    public void setTopicCreatetime(String topicCreatetime) {
        this.topicCreatetime = topicCreatetime;
    }

    public String getTopicBrief() {
        return topicBrief;
    }

    public void setTopicBrief(String topicBrief) {
        this.topicBrief = topicBrief;
    }

    public String getFensiCount() {
        return fensiCount;
    }

    public void setFensiCount(String fensiCount) {
        this.fensiCount = fensiCount;
    }

    public String getTieziCount() {
        return tieziCount;
    }

    public void setTieziCount(String tieziCount) {
        this.tieziCount = tieziCount;
    }
}
