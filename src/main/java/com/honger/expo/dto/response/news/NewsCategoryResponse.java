package com.honger.expo.dto.response.news;

import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.News;

/**
 * Created by chenjian on 2018/4/19.
 */
public class NewsCategoryResponse extends News{
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
