package com.honger.expo.service;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.ExhibitionCategory;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<CategoryListResponse> getHomePageCategory();

    List<Category> getAllCategory();

    String getCategoryIdByName(String category);

    Category getCategoryById(String categoryId);
}
