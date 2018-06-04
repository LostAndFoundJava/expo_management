package com.honger.expo.service;

import com.honger.expo.dto.HomePageConfig;
import com.honger.expo.dto.response.aboutUs.AboutUsResponse;

import java.util.List;

public interface HomePageConfigService {
    List<HomePageConfig> getHomePageConfig(String exhibitionId);
}
