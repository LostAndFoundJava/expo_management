package com.honger.expo.dao;

import com.honger.expo.pojo.ClickCount;
import com.honger.expo.pojo.Exhibition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClickCountMapper {
    //查询是否有展会记录
    Integer selectExistByExhibitionId(@Param("exhibitionId") String exhibitionId,@Param("type") Integer type);

    //插入一条新访问次数记录
    void insertExhibitionIdCount(ClickCount exhibitionCount);

    //update增加一次访问记录
    void updateExhibitionIdCount(@Param("exhibitionId") String exhibitionId,@Param("type") Integer type);
    
    //查询展会访问的次数
    Integer selectCountByExhibitionId(@Param("exhibitionId") String ExhibitionId);

    //返回top个排序的展会
    List<ClickCount> getTopClickExhibition(@Param("top") Integer top,@Param("type") Integer type);

    Exhibition selectExhibtionById(@Param("ExhibitionId") String ExhibitionId);
}