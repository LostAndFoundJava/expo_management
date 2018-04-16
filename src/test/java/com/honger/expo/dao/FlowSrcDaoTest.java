package com.honger.expo.dao;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.FlowSrc;
import com.honger.expo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class FlowSrcDaoTest {
    @Autowired
    private FlowSrcMapper flowSrcMapper;

    @Test
    public void insert(){
        FlowSrc fs = new FlowSrc();
        fs.setClientName("cj");
        fs.setCreateTime(new Date());
        fs.setUpdateTime(new Date());
        flowSrcMapper.insert(fs);
    }
}