package com.honger.expo.service.impl;

import com.honger.expo.dao.NewsMapper;
import com.honger.expo.dto.response.news.NewsCategoryResponse;
import com.honger.expo.pojo.News;
import com.honger.expo.service.NewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class NewsServiceTest {
    @Autowired
    private NewService newService;

    @Test
    public void getAllNews() throws InvocationTargetException, IllegalAccessException {
        List<NewsCategoryResponse> allNews = newService.getAllNews();
        System.out.println(allNews);
    }
}