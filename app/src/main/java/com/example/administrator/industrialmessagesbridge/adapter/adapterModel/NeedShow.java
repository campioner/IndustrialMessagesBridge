package com.example.administrator.industrialmessagesbridge.adapter.adapterModel;

import android.os.Parcel;
import android.os.Parcelable;

public class NeedShow implements Parcelable{//序列化
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
       parcel.writeInt(needId);
        parcel.writeString(need_title);
        parcel.writeString(need_position);
        parcel.writeString(need_price);
        parcel.writeString(need_background);
        parcel.writeString(need_time);
        parcel.writeString(require_person);
        parcel.writeString(need_person);
        parcel.writeString(need_deadline);
    }
    public static final Parcelable.Creator<NeedShow>CREATOR=new Parcelable.Creator<NeedShow>(){
        @Override
        public NeedShow createFromParcel(Parcel parcel) {
            return new NeedShow(parcel);
        }

        @Override
        public NeedShow[] newArray(int i) {
            return new NeedShow[i];
        }
    };
    private int needId;
    private String need_title;
    private String need_position;
    private String need_price;
    private String need_background;
    private String need_time;
    private String require_person;
    private String need_person;
    private String need_deadline;
public NeedShow(Parcel in){
    needId=in.readInt();
    need_title=in.readString();
    need_position=in.readString();
    need_price=in.readString();
    need_background=in.readString();
    need_time=in.readString();
    require_person=in.readString();
    need_person= in.readString();
    need_deadline=in.readString();
}
    public NeedShow(int needId,String need_title, String need_position, String need_price, String need_background,
                    String need_time, String require_person, String need_person, String need_deadline) {
        this.needId=needId;
        this.need_title = need_title;
        this.need_position = need_position;
        this.need_price = need_price;
        this.need_background = need_background;
        this.need_time = need_time;
        this.require_person = require_person;
        this.need_person = need_person;
        this.need_deadline = need_deadline;
    }

    public int getNeedId() {
        return needId;
    }

    public void setNeedId(int needId) {
        this.needId = needId;
    }

    public String getNeed_title() {
        return need_title;
    }

    public void setNeed_title(String need_title) {
        this.need_title = need_title;
    }

    public String getNeed_position() {
        return need_position;
    }

    public void setNeed_position(String need_position) {
        this.need_position = need_position;
    }

    public String getNeed_price() {
        return need_price;
    }

    public void setNeed_price(String need_price) {
        this.need_price = need_price;
    }

    public String getNeed_background() {
        return need_background;
    }

    public void setNeed_background(String need_background) {
        this.need_background = need_background;
    }

    public String getNeed_time() {
        return need_time;
    }

    public void setNeed_time(String need_time) {
        this.need_time = need_time;
    }

    public String getRequire_person() {
        return require_person;
    }

    public void setRequire_person(String require_person) {
        this.require_person = require_person;
    }

    public String getNeed_person() {
        return need_person;
    }

    public void setNeed_person(String need_person) {
        this.need_person = need_person;
    }

    public String getNeed_deadline() {
        return need_deadline;
    }

    public void setNeed_deadline(String need_deadline) {
        this.need_deadline = need_deadline;
    }
}
