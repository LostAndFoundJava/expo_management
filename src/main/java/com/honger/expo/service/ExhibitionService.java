package com.honger.expo.service;

import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.vo.ExhibitionAndDetailVO;
import com.honger.expo.dto.vo.ExhibitionHomePage;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.ExhibitionDetail;
import com.honger.expo.pojo.RegionData;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ExhibitionService {
    List<ExhibitionSearchVO> getExhibitionByCondition(String country, String categories, String date);

    List<ExhibitionSearchVO> searchExhibition(String condition);

    Integer getTotalNumByConditon (String country, String categories,String date);

    ExhibitionDetailResponse getDetail(String exhibitionId) throws InvocationTargetException, IllegalAccessException;

    Map<String,List<ExhibitionSearchVO>> getHomePage() throws InvocationTargetException, IllegalAccessException;

    Integer getTotalNumBySearch(String query);

}
