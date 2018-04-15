package com.honger.expo.service;

import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.RegionData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExhibitionService {
    List<ExhibitionSearchVO> getExhibitionByCondition(String country, String categories, String date);

    List<ExhibitionSearchVO> searchExhibition(String condition);
}
