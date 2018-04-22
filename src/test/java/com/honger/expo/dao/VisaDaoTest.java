package com.honger.expo.dao;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.vo.VisaVO;
import com.honger.expo.pojo.Category;
import com.honger.expo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class VisaDaoTest {
    @Autowired
    private VisaMapper visaMapper;

    @Test
    public void getVisaByCountryId(){
        List<VisaVO> visaByCountryId = visaMapper.getVisaByCountryId("7");
        System.out.println(visaByCountryId);
    }
}

