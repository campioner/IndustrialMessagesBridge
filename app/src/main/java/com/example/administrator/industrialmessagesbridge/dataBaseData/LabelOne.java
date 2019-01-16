package com.example.administrator.industrialmessagesbridge.dataBaseData;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class LabelOne {
    @Id
    private Long id;
    private String lableId;
    private String name;
    @Generated(hash = 1060589119)
    public LabelOne(Long id, String lableId, String name) {
        this.id = id;
        this.lableId = lableId;
        this.name = name;
    }

    @Generated(hash = 1052517948)
    public LabelOne() {
    }
    public String getLableId() {
        return lableId;
    }

    public void setLableId(String lableId) {
        this.lableId = lableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
