package com.honger.expo.service.impl;

import com.github.pagehelper.PageHelper;
import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.ExhibitionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ExhibitionMapperTest {

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
}