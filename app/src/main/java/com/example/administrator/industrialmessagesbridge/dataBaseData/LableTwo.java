package com.example.administrator.industrialmessagesbridge.dataBaseData;

import com.contrarywind.interfaces.IPickerViewData;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LableTwo implements IPickerViewData {
    @Id
    private Long id;
    private String lableOneId;
    private String lableTwoId;
    private String name;

    @Generated(hash = 111982137)
    public LableTwo(Long id, String lableOneId, String lableTwoId, String name) {
        this.id = id;
        this.lableOneId = lableOneId;
        this.lableTwoId = lableTwoId;
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }

    @Generated(hash = 428960019)
    public LableTwo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLableOneId() {
        return lableOneId;
    }

    public void setLableOneId(String lableOneId) {
        this.lableOneId = lableOneId;
    }

    public String getLableTwoId() {
        return lableTwoId;
    }

    public void setLableTwoId(String lableTwoId) {
        this.lableTwoId = lableTwoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
