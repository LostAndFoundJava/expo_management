package com.honger.expo.dao;

import com.honger.expo.pojo.Advice;
import com.honger.expo.pojo.FlowSrc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@WebAppConfiguration
public class AdviceDaoTest {
    @Autowired
    private AdviceMapper adviceMapper;

    @Test
    public void insert(){
        Advice a = new Advice();
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replace("-","");
        a.setId(s);
        a.setMobileNo("11111");
        a.setDelete("0");
        a.setCreateTime(new Date());
        a.setUpdateTime(new Date());
        adviceMapper.insert(a);
    }
}