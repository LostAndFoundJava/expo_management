package com.honger.expo.dto.vo;

import com.honger.expo.pojo.News;

/**
 * Created by chenjian on 2018/4/19.
 */
public class NewsCategoryVO extends News {
    private String categoryId;
    private String name;

    @Override
    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
