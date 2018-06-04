package com.honger.expo.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.honger.expo.pojo.RegionData;
import org.apache.ibatis.annotations.Param;


public interface RegionDataMapper {
    /**
     * continent条件返回，为空返回全部
     * @return 全部地区信息
     */
    List<RegionData> selectCondition(@Param("continent") String continent);

    String getRegionIdByCountryName(@Param("country") String country);

    RegionData getRegionCityById(@Param("city") Integer city);

    RegionData getRegionCountryById(@Param("country") Integer country);

    TreeSet<RegionData> getRegionCountryByContinent(@Param("continent") String continent);
}