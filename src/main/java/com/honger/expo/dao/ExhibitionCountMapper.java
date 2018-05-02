package com.honger.expo.dao;

import com.honger.expo.pojo.ExhibitionCount;
import org.apache.ibatis.annotations.Param;

public interface ExhibitionCountMapper {
    //查询是否有展会记录
    Integer selectExistByExhibitionId(@Param("exhibitionId") String exhibitionId);

    //插入一条新访问次数记录
    void insertExhibitonIdCount(ExhibitionCount exhibitionCount);

    //update增加一次访问记录
    void updateExhibitionIdCount(@Param("exhibitionId") String exhibitionId);
    
    //查询展会访问的次数
    Integer selectCountByExhibitonId(@Param("exhibitionId") String ExhibitionId);
}