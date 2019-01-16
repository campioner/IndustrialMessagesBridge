package com.example.administrator.industrialmessagesbridge.model;

import com.contrarywind.interfaces.IPickerViewData;

public class LableOneModel implements IPickerViewData{
    private String lableOneId;
    private String lableName;
    private String description;
    private String others;

    public LableOneModel(String lableOneId, String lableName, String description, String others) {
        this.lableOneId = lableOneId;
        this.lableName = lableName;
        this.description = description;
        this.others = others;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getLableOneId() {
        return lableOneId;
    }

    public void setLableOneId(String lableOneId) {
        this.lableOneId = lableOneId;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    @Override
    public String getPickerViewText() {
        return lableName;
    }
}
