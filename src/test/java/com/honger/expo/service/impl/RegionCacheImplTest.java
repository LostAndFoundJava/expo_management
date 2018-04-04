package com.honger.expo.service.impl;

import com.honger.expo.pojo.RegionData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RegionCacheImplTest {

    @Autowired
    private RegionCacheImpl regionCache;

    @Test
    public void getRegionDataByPk() {
        RegionData regionData = regionCache.getRegionDataByPk(1);

        System.out.println(regionData.getName());
    }
}