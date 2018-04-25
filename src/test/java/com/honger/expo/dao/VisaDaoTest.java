package com.honger.expo.dao;

import com.honger.expo.dto.vo.VisaVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class VisaDaoTest {
    @Autowired
    private VisaMapper visaMapper;

    @Test
    public void getVisaByCountryId(){
        List<VisaVO> visaByCountryId = visaMapper.getVisaByCountryId("89");
        System.out.println(visaByCountryId);
    }
}

