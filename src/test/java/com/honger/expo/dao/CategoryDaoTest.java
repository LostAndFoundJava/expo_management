package com.honger.expo.dao;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.Category;
import com.honger.expo.service.CategoryService;
import lombok.Synchronized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CategoryDaoTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testGetHomePageCategory() throws InterruptedException {
        List<CategoryListResponse> homePageCategory = categoryService.getHomePageCategory();
        System.out.println();
    }

    @Test
    public void testGetAllCategory(){
        List<Category> allCategory = categoryMapper.getAllCategory();
        System.out.println(allCategory);
    }
}

