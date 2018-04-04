package com.honger.expo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RegionDataDaoTest {

    @Autowired
    private RegionDataDao regionDataDao;

    @Test
    public void selectByPrimaryKey() {
        System.out.println(regionDataDao.selectByPrimaryKey(1));
    }

    @Test
    public void selectAll() {
        System.out.println(regionDataDao.selectAll().size());
    }
}