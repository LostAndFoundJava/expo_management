package com.honger.expo.service.impl;

import com.honger.expo.dao.AboutUsMapper;
import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.pojo.AboutUs;
import com.honger.expo.service.AboutUsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class AboutUsImplTest {
    @Autowired
    private AboutUsService aboutUsService;

    @Test
    public void getVisaByCountryId(){
        List<AboutUsResponse> aboutUs = aboutUsService.getAboutUs();
        System.out.println(aboutUs);
    }
}

