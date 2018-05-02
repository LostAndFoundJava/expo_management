package com.honger.expo.service;

import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.pojo.ExhibitionCount;

import java.util.List;

public interface ExhibitionCountService {
    void insertOrUpdateExhibitionCount(String exhibitionId);

    Integer selectCountByExhibitionId(String exhibitionId);
}
