package com.honger.expo.pojo;

/**
 * 分类信息联合表
 */
public class ExhibitionCategory {

    /**
     * 展会所属国家Id
     */
    private Integer country;
    /**
     * 展会所属行业编号
     */
    private String id;
    /**
     * 展会所属行业的父行业编号
     */
    private String parentId;
    /**
     * 展会所属行业的名称
     */
    private String name;

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExhibitionCategory{" +
                "country=" + country +
                ", id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
