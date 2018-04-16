package com.honger.expo.dao;

import com.github.pagehelper.PageHelper;
import com.honger.expo.dto.vo.ExhibitionAndDetailVO;
import com.honger.expo.dto.vo.ExhibitionHomePage;
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
        List<Exhibition> exhibitionByCondition = exhibitionMapper.getExhibitionByCondition("", "", "2018-11-31","2018-11-01");
        System.out.println(exhibitionByCondition);
    }

    @Test
    public void searchExhibiton() {
        List<Exhibition> title = exhibitionMapper.searchExhibition("title");
        System.out.println(title);
    }

    @Test
    public void getExhibitonDetail() {
        List<ExhibitionAndDetailVO> detial = exhibitionMapper.getDetial("8a5aec9585b24b4ea575bd319f6aec1b");
        System.out.println(detial);
    }

    @Test
    public void getHomepage() {
        List<ExhibitionHomePage> homePage = exhibitionMapper.getHomePage();
        System.out.println(homePage);
    }
}