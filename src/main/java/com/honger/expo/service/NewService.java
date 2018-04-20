package com.honger.expo.service;

import com.honger.expo.dto.response.news.NewsCategoryResponse;
import com.honger.expo.dto.vo.ExhibitionSearchVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface NewService {
    List<NewsCategoryResponse> getAllNews() throws InvocationTargetException, IllegalAccessException;

    List<String> allNewsCategory();

    List<NewsCategoryResponse> newsCategory(String newsCategory) throws InvocationTargetException, IllegalAccessException;

    List<NewsCategoryResponse> newsById(String id) throws InvocationTargetException, IllegalAccessException;

    List<ExhibitionSearchVO> getRelationExhibitionByNewsId(String id);

    int getTotalNum();

    int getNewsCategoryNum(String newsCategory);
}
