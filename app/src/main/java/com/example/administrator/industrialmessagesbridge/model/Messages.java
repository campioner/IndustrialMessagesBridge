package com.example.administrator.industrialmessagesbridge.model;

public class Messages {
    private Integer messages_id;
    private Integer messages_type;
    private String messages_info;
    private String messages_time;
    private Integer messages_collectnum;
    private Integer messages_commentnum;
    private Integer messages_transpondnum;
    private Integer messages_agreenum;
    private Integer messages_readnum;
    private Integer messages_tab;
    private Integer messages_price;
    private String user_name;
    private Integer user_id;

    public Messages(Integer messages_id, Integer messages_type, String messages_info, String messages_time, Integer messages_collectnum, Integer messages_commentnum, Integer messages_transpondnum, Integer messages_agreenum, Integer messages_readnum, Integer messages_tab, Integer messages_price, String user_name, Integer user_id) {
        this.messages_id = messages_id;
        this.messages_type = messages_type;
        this.messages_info = messages_info;
        this.messages_time = messages_time;
        this.messages_collectnum = messages_collectnum;
        this.messages_commentnum = messages_commentnum;
        this.messages_transpondnum = messages_transpondnum;
        this.messages_agreenum = messages_agreenum;
        this.messages_readnum = messages_readnum;
        this.messages_tab = messages_tab;
        this.messages_price = messages_price;
        this.user_name = user_name;
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getMessages_id() {
        return messages_id;
    }

    public void setMessages_id(Integer messages_id) {
        this.messages_id = messages_id;
    }

    public Integer getMessages_type() {
        return messages_type;
    }

    public void setMessages_type(Integer messages_type) {
        this.messages_type = messages_type;
    }

    public String getMessages_info() {
        return messages_info;
    }

    public void setMessages_info(String messages_info) {
        this.messages_info = messages_info;
    }

    public String getMessages_time() {
        return messages_time;
    }

    public void setMessages_time(String messages_time) {
        this.messages_time = messages_time;
    }

    public Integer getMessages_collectnum() {
        return messages_collectnum;
    }

    public void setMessages_collectnum(Integer messages_collectnum) {
        this.messages_collectnum = messages_collectnum;
    }

    public Integer getMessages_commentnum() {
        return messages_commentnum;
    }

    public void setMessages_commentnum(Integer messages_commentnum) {
        this.messages_commentnum = messages_commentnum;
    }

    public Integer getMessages_transpondnum() {
        return messages_transpondnum;
    }

    public void setMessages_transpondnum(Integer messages_transpondnum) {
        this.messages_transpondnum = messages_transpondnum;
    }

    public Integer getMessages_agreenum() {
        return messages_agreenum;
    }

    public void setMessages_agreenum(Integer messages_agreenum) {
        this.messages_agreenum = messages_agreenum;
    }

    public Integer getMessages_readnum() {
        return messages_readnum;
    }

    public void setMessages_readnum(Integer messages_readnum) {
        this.messages_readnum = messages_readnum;
    }

    public Integer getMessages_tab() {
        return messages_tab;
    }

    public void setMessages_tab(Integer messages_tab) {
        this.messages_tab = messages_tab;
    }

    public Integer getMessages_price() {
        return messages_price;
    }

    public void setMessages_price(Integer messages_price) {
        this.messages_price = messages_price;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
