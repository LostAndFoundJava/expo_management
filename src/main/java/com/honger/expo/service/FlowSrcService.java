package com.honger.expo.service;

import com.honger.expo.pojo.FlowSrc;
import com.honger.expo.pojo.RegionData;

import java.util.List;

public interface FlowSrcService {

    String insert(FlowSrc flowSrc);

    void update(FlowSrc flowSrc);

    boolean isMobileUnique(String mobileNo,String exhibitionId);
}
