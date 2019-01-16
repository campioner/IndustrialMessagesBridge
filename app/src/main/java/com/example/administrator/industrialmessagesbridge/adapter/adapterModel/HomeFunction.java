package com.example.administrator.industrialmessagesbridge.adapter.adapterModel;

public class HomeFunction {
    private Integer functionPhoto;
    private String functionText;

    public HomeFunction(Integer functionPhoto, String functionText) {
        this.functionPhoto = functionPhoto;
        this.functionText = functionText;
    }

    public Integer getFunctionPhoto() {
        return functionPhoto;
    }

    public void setFunctionPhoto(Integer functionPhoto) {
        this.functionPhoto = functionPhoto;
    }

    public String getFunctionText() {
        return functionText;
    }

    public void setFunctionText(String functionText) {
        this.functionText = functionText;
    }
}
