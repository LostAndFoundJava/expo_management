package com.honger.expo.service;

import com.honger.expo.dto.vo.ClickCountExhibition;
import com.honger.expo.pojo.ClickCount;

import java.util.List;

public interface ExhibitionCountService {
    void insertOrUpdateExhibitionCount(String exhibitionId);

    Integer selectCountByExhibitionId(String exhibitionId);

    List<ClickCountExhibition> getTopClickExhibiton(String top);
}
