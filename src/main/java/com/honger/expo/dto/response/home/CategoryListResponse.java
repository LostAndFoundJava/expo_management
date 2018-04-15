package com.honger.expo.dto.response.home;

import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.RegionData;

import java.util.List;
import java.util.Set;

/*
* [
    {
        "title" : "建材/石材/照明/暖通",
        "id" : "1",
        "subCategory" : ["目前不需要"],
        "country" : [
            {
                "title" : "美国",
                "pinyin" : "meiguo",
                "id" : "1"
            },
            {
                "title" : "中国",
                "pinyin" : "meiguo",
                "id" : "1"
            }
        ]
    }
]*/
public class CategoryListResponse {
    private String title;
    private String id;
    private String subCategory;
    private Set<RegionData> country;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Set<RegionData> getCountry() {
        return country;
    }

    public void setCountry(Set<RegionData> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CategoryListResponse{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", country=" + country +
                '}';
    }
}
