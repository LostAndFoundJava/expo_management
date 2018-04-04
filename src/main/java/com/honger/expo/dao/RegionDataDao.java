package com.honger.expo.dao;

import java.util.List;

import com.honger.expo.pojo.RegionData;

public interface RegionDataDao {

    RegionData selectByPrimaryKey(Integer id);

    /**
     * 获取全部地区信息，用于放入内存缓存
     * @return 全部地区信息
     */
    List<RegionData> selectAll();
}