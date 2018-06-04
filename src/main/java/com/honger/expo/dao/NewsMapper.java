package com.honger.expo.dao;

import com.honger.expo.dto.response.news.NewsCategoryResponse;
import com.honger.expo.dto.vo.NewsCategoryVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    //获得所有的新闻
    List<NewsCategoryVO> getAllNews();

    //获得所有的新闻分类
    List<String> getAllNewsCategory();

    List<NewsCategoryVO> getNewsCategory(@Param("newsCategory") String newsCategory);

    NewsCategoryVO getNewsById(@Param("id") String id);

    List<Exhibition> getRelationExhibitionByNewsId(@Param("id") String id);

    int getTotalNum();

    int getNewsCategoryNum(@Param("newsCategory") String newsCategory);
}