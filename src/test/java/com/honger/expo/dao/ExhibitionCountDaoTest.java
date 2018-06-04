package com.honger.expo.dao;

import com.honger.expo.pojo.ClickCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.UUID;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ExhibitionCountDaoTest {
    @Autowired
    private ClickCountMapper clickCountMapper;

    @Test
    public void getVisaByCountryId(){
        Integer integer = clickCountMapper.selectExistByExhibitionId("163ca4e3993745c084ccec33917b6b94",0);
        if(integer.equals(0)){
            ClickCount ec = new ClickCount();
            UUID uuid = UUID.randomUUID();
            String s = uuid.toString().replace("-","");
            ec.setCreateTime(new Date());
            ec.setUpdateTime(new Date());
            ec.setId(s);
            ec.setClickedId("163ca4e3993745c084ccec33917b6b94");
            clickCountMapper.insertExhibitionIdCount(ec);
        }else{
            clickCountMapper.updateExhibitionIdCount("163ca4e3993745c084ccec33917b6b94",0);
        }
    }
}

