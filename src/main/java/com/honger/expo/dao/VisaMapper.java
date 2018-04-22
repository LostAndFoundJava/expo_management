package com.honger.expo.dao;


import com.honger.expo.dto.vo.VisaVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisaMapper {
    //查询国家下面的签证
    List<VisaVO> getVisaByCountryId(@Param("countryId") String countryId);
}