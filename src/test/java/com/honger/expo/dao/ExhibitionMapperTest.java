package com.honger.expo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ExhibitionMapperTest {

    @Autowired
    private ExhibitionMapper exhibitionMapper;

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectHotCategory() {

        List<String> result = exhibitionMapper.selectHotCategory();

        System.out.println(result);

    }
}