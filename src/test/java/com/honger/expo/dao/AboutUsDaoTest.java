package com.honger.expo.dao;

import com.honger.expo.dto.vo.VisaVO;
import com.honger.expo.pojo.AboutUs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class AboutUsDaoTest {
    @Autowired
    private AboutUsMapper aboutUsMapper;

    @Test
    public void getVisaByCountryId(){
        List<AboutUs> aboutUs = aboutUsMapper.getAboutUs();
        System.out.println(aboutUs);
    }
}

