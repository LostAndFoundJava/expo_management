package com.honger.expo.dao;

import com.honger.expo.pojo.ExhibitionCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ExhibitionCategoryDaoTest {

    @Autowired
    private ExhibitionCategoryDao exhibitionCategoryDao;
    @Test
    public void queryHotCatExhibition() {

        List<ExhibitionCategory> list = exhibitionCategoryDao.queryHotCatExhibition();

        System.out.println(list);
    }
}