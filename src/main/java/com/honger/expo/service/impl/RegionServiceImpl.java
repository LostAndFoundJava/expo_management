package com.honger.expo.service.impl;

import com.honger.expo.dao.RegionDataMapper;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenjian on 2018/4/13.
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDataMapper regionDataMapper;

    @Override
    public List<RegionData> selectCondition(String continent) {
        return regionDataMapper.selectCondition(continent);
    }

    @Override
    public String getRegionIdByCountryName(String country) {
        return regionDataMapper.getRegionIdByCountryName(country);
    }

    @Override
    public RegionData getRegionCityById(Integer city) {
        return regionDataMapper.getRegionCityById(city);
    }

    @Override
    public RegionData getRegionCountryById(Integer country) {
        return regionDataMapper.getRegionCountryById(country);
    }
}
