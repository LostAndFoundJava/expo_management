package com.honger.expo.dto.vo;

import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.RegionData;

/**
 * Created by chenjian on 2018/4/4.
 */
public class CategoryExhibitonRegionVO {
    private String name;
    private String id;
    private String rName;
    private Integer rId;
    private String namePinyin;
    private String nameEn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public String toString() {
        return "CategoryExhibitonRegionVO{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", rName='" + rName + '\'' +
                ", rId=" + rId +
                ", namePinyin='" + namePinyin + '\'' +
                ", nameEn='" + nameEn + '\'' +
                '}';
    }
}
