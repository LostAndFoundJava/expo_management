package com.honger.expo.dao;


import com.honger.expo.dto.HomePageConfig;
import com.honger.expo.pojo.AboutUs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomePageConfigMapper {
    //查询首页配置
    List<HomePageConfig> getHomePageConfig(@Param("exhibitionId") String exhibitionId);
}