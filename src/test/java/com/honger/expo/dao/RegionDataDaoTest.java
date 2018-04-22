package com.honger.expo.dao;

import com.honger.expo.pojo.RegionData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RegionDataDaoTest {

    @Autowired
    private RegionDataMapper regionDataDao;

    @Test
    public void selectAll() {
        List<RegionData> asia = regionDataDao.selectCondition("");
        System.out.println(asia);
    }

    @Test
    public void selecRegionById() {
        RegionData regionCountryById = regionDataDao.getRegionCountryById(16);
        System.out.println(regionCountryById);

        RegionData regionCityById = regionDataDao.getRegionCityById(1);
        System.out.println(regionCityById);
    }

    @Test
    public void getRegionCountryByContinent() {
        List<RegionData> regionCountryByContinent = regionDataDao.getRegionCountryByContinent("1");
        System.out.println(regionCountryByContinent);
    }
}