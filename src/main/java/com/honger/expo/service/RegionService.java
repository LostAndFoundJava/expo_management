package com.honger.expo.service;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.RegionData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionService {

    List<RegionData> selectCondition(String continent);

    String getRegionIdByCountryName(String country);

    RegionData getRegionCityById(Integer city);

    RegionData getRegionCountryById(Integer country);
}
