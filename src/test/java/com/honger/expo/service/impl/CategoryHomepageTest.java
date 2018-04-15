package com.honger.expo.service.impl;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CategoryHomepageTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void getCategoryHomepage(){
        List<CategoryListResponse> homePageCategory = categoryService.getHomePageCategory();
        System.out.println();
    }
}