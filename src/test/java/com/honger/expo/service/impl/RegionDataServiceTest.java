package com.honger.expo.service.impl;

import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.RegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RegionDataServiceTest {

    @Autowired
    private RegionService regionService;

    @Test
    public void selectAll() {
        List<RegionData> asia = regionService.selectCondition("");
        System.out.println(asia);
    }
}