package com.honger.expo.service.impl;

import com.github.pagehelper.PageHelper;
import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.vo.ExhibitionAndDetailVO;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.ExhibitionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ExhibitionServiceTest {

    @Autowired
    private ExhibitionService exhibitionService;

    @Test
    public void getExhibitonByCondition() {
        PageHelper.startPage(1,3);
        List<ExhibitionSearchVO> exhibitionByCondition = exhibitionService.getExhibitionByCondition("", "", "2018/11");
        System.out.println(exhibitionByCondition);
    }

    @Test
    public void searchExhibiton() {
        List<ExhibitionSearchVO> title = exhibitionService.searchExhibition("title");
        System.out.println(title);
    }

    @Test
    public void getExhibitonDetail() throws InvocationTargetException, IllegalAccessException {
        ExhibitionDetailResponse detail = exhibitionService.getDetail("8a5aec9585b24b4ea575bd319f6aec1b");
        System.out.println(detail);
    }

    @Test
    public void getHomeExhibiton() throws InvocationTargetException, IllegalAccessException {
        Map<String, List<ExhibitionSearchVO>> homePage = exhibitionService.getHomePage();
        System.out.println(homePage);
    }
}