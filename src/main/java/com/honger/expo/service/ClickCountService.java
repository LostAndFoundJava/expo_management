package com.honger.expo.service;

import com.honger.expo.dto.vo.ClickCountVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.utils.CountType;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ClickCountService {
    void insertOrUpdateExhibitionCount(String exhibitionId,Integer type) throws InvocationTargetException, IllegalAccessException;

    Integer selectCountByExhibitionId(String exhibitionId);

    List<ClickCountVO> getTopClickExhibition(String top, CountType conuntType);
}
