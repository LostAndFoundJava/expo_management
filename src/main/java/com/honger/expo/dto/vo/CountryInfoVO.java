package com.honger.expo.dto.vo;

public class CountryInfoVO {

    private String name;

    private Integer id;

    public CountryInfoVO() {
    }

    public CountryInfoVO(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
