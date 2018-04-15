package com.honger.expo.dao;

import com.honger.expo.pojo.Exhibition;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ExhibitionMapper {
    //根据指定条件查询展会
    List<Exhibition> getExhibitionByCondition(@Param("country") String country,
                                              @Param("categories") String categories,
                                              @Param("date") String date);

    //搜索条件
    List<Exhibition> searchExhibition(@Param("condition") String condition);
}