package com.honger.expo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honger.expo.dao.AboutUsMapper;
import com.honger.expo.dao.HomePageConfigMapper;
import com.honger.expo.dto.HomePageConfig;
import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.pojo.AboutUs;
import com.honger.expo.service.AboutUsService;
import com.honger.expo.service.HomePageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomePageConfigServiceImpl implements HomePageConfigService{
    @Autowired
    private HomePageConfigMapper homePageConfigMapper;

    @Override
    public List<HomePageConfig> getHomePageConfig(String exhibitionId) {
        return homePageConfigMapper.getHomePageConfig(exhibitionId);
    }
}
