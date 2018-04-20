package com.honger.expo.dao;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.news.NewsCategoryResponse;
import com.honger.expo.dto.vo.NewsCategoryVO;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.News;
import com.honger.expo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class NewsDaoTest {
    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void getAllNews(){
        List<NewsCategoryVO> allNews = newsMapper.getAllNews();
        System.out.println(allNews);
    }

    @Test
    public void getAllNewsCategory(){
        List<String> allNewsCategory = newsMapper.getAllNewsCategory();
        System.out.println(allNewsCategory);
    }

    @Test
    public void getNewsCategory(){
        List<NewsCategoryVO> list = newsMapper.getNewsCategory("咨讯");
        System.out.println(list);
    }

    @Test
    public void getNewsById(){
        NewsCategoryVO newsById = newsMapper.getNewsById("2f41049df4a941f39ff535d339286272");
        System.out.println(newsById);
    }

    @Test
    public void getRelationExhibitionByNewsId(){
        List<Exhibition> relationExhibitionByNewsId = newsMapper.getRelationExhibitionByNewsId("2f41049df4a941f39ff535d339286272");
        System.out.println(relationExhibitionByNewsId);
    }
}