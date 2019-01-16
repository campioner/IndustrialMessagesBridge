package com.example.administrator.industrialmessagesbridge.model;

import android.os.Parcel;
import android.os.Parcelable;




public class TopicContent  implements Parcelable {
    private int topicId;
    private String time;
    private String name;
    private String content;
    private Integer image;
    private Integer imageOne;
    private Integer imageTwo;
    private Integer imageThree;
    private Integer goodCounds;
    private String twoLableName;
    public TopicContent(String time, String name, String content, Integer image, Integer imageOne, Integer imageTwo, Integer imageThree) {
        this.time = time;
        this.name = name;
        this.content = content;
        this.image = image;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public TopicContent(Parcel parcel){
        topicId=parcel.readInt();
        time=parcel.readString();
        name=parcel.readString();
        content=parcel.readString();
        image=parcel.readInt();
        imageOne=parcel.readInt();
        imageTwo=parcel.readInt();
        imageThree=parcel.readInt();
        goodCounds=parcel.readInt();
        twoLableName=parcel.readString();
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
      parcel.writeInt(topicId);
      parcel.writeString(time);
        parcel.writeString(name);
        parcel.writeString(content);
        parcel.writeInt(image.intValue());
        parcel.writeInt(imageOne.intValue());
        parcel.writeInt(imageTwo.intValue());
        parcel.writeInt(imageThree.intValue());
        parcel.writeInt(goodCounds.intValue());
        parcel.writeString(twoLableName);
    }
    public static final Parcelable.Creator<TopicContent>CREATOR=new Parcelable.Creator<TopicContent>(){
        @Override
        public TopicContent createFromParcel(Parcel parcel) {
            return new TopicContent(parcel);
        }

        @Override
        public TopicContent[] newArray(int i) {
            return new TopicContent[i];
        }
    };

    public String getTwoLableName() {
        return twoLableName;
    }

    public void setTwoLableName(String twoLableName) {
        this.twoLableName = twoLableName;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public Integer getGoodCounds() {
        return goodCounds;
    }

    public void setGoodCounds(Integer goodCounds) {
        this.goodCounds = goodCounds;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getImageOne() {
        return imageOne;
    }

    public void setImageOne(Integer imageOne) {
        this.imageOne = imageOne;
    }

    public Integer getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(Integer imageTwo) {
        this.imageTwo = imageTwo;
    }

    public Integer getImageThree() {
        return imageThree;
    }

    public void setImageThree(Integer imageThree) {
        this.imageThree = imageThree;
    }
}
