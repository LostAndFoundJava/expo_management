package com.honger.expo.dao;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.Category;
import com.honger.expo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CategoryDaoTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testGetHomePageCategory(){
        List<CategoryListResponse> homePageCategory = categoryService.getHomePageCategory();
        System.out.println();
    }

    @Test
    public void testGetAllCategory(){
        List<Category> allCategory = categoryMapper.getAllCategory();
        System.out.println(allCategory);
    }
}