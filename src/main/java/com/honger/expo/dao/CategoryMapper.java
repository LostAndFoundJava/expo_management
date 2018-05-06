package com.honger.expo.dao;


import java.util.List;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.vo.CategoryExhibitionRegionVO;
import com.honger.expo.pojo.Category;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {

    //查询分类栏
    List<CategoryExhibitionRegionVO> getHomePageCategory();

    //获得所有的分类
    List<Category> getAllCategory();

    String getCategoryIdByName(@Param("category") String category);

    Category getCategoryById(@Param("categoryId") String categoryId);
}