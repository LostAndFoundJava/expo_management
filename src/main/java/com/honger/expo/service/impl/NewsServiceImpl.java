package com.honger.expo.service.impl;

import com.honger.expo.dao.CategoryMapper;
import com.honger.expo.dao.NewsMapper;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.news.NewsCategoryResponse;
import com.honger.expo.dto.vo.CategoryExhibitionRegionVO;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.dto.vo.NewsCategoryVO;
import com.honger.expo.pojo.*;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ClickCountService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.NewService;
import com.honger.expo.utils.BeanReflectUtil;
import com.honger.expo.utils.CountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NewsServiceImpl implements NewService {
    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private ClickCountService clickCountService;

    @Override
    public List<NewsCategoryResponse> getAllNews() throws InvocationTargetException, IllegalAccessException {
        List<NewsCategoryVO> allNews = newsMapper.getAllNews();
        List<NewsCategoryResponse> list = new ArrayList<>();
        transform(allNews, list);
        return list;
    }

    private void transform(List<NewsCategoryVO> allNews, List<NewsCategoryResponse> list) throws InvocationTargetException, IllegalAccessException {
        for(NewsCategoryVO ncv : allNews){
            Integer integer = clickCountService.selectCountByExhibitionId(ncv.getId(), CountType.news);

            NewsCategoryResponse ncr = new NewsCategoryResponse();

            BeanReflectUtil.beanSet(ncv,ncr);

            ncr.setClickCount(integer.toString());

            Category category = new Category();
            category.setId(ncv.getCategoryId());
            category.setName(ncv.getName());
            ncr.setCategory(category);

            list.add(ncr);
        }
    }

    @Override
    public List<String> allNewsCategory() {
        return newsMapper.getAllNewsCategory();
    }

    @Override
    public List<NewsCategoryResponse> newsCategory(String newsCategory) throws InvocationTargetException, IllegalAccessException {
        List<NewsCategoryVO> newsCategoryList = newsMapper.getNewsCategory(newsCategory);
        List<NewsCategoryResponse> list = new ArrayList<>();
        transform(newsCategoryList, list);
        return list;
    }

    @Override
    public List<NewsCategoryResponse> newsById(String id) throws InvocationTargetException, IllegalAccessException {
        NewsCategoryVO newsById = newsMapper.getNewsById(id);

        if(newsById == null){
            return new ArrayList<>();
        }

        List<NewsCategoryVO> newsList = new ArrayList<>();
        newsList.add(newsById);

        List<NewsCategoryResponse> list = new ArrayList<>();
        transform(newsList, list);
        return list;
    }

    @Override
    public List<ExhibitionSearchVO> getRelationExhibitionByNewsId(String id) {
        List<Exhibition> relationExhibitionByNewsId = newsMapper.getRelationExhibitionByNewsId(id);
        return ((ExhibitionServiceImpl)exhibitionService).getExhibitionSearchVOS(relationExhibitionByNewsId);
    }

    @Override
    public int getTotalNum() {
        return newsMapper.getTotalNum();
    }

    @Override
    public int getNewsCategoryNum(String newsCategory) {
        return newsMapper.getNewsCategoryNum(newsCategory);
    }
}
