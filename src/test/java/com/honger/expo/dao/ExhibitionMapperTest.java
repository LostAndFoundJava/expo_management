package com.honger.expo.dao;

import com.github.pagehelper.PageHelper;
import com.honger.expo.pojo.Exhibition;
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
    public void getExhibitonByCondition() {
        PageHelper.startPage(1,3);
        List<Exhibition> exhibitionByCondition = exhibitionMapper.getExhibitionByCondition("", "", "");
        System.out.println(exhibitionByCondition);
    }

    @Test
    public void searchExhibiton() {
        List<Exhibition> title = exhibitionMapper.searchExhibition("title");
        System.out.println(title);
    }
}