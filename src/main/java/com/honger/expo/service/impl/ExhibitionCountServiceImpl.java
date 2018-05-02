package com.honger.expo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honger.expo.dao.AboutUsMapper;
import com.honger.expo.dao.ExhibitionCountMapper;
import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.pojo.AboutUs;
import com.honger.expo.pojo.ExhibitionCount;
import com.honger.expo.service.AboutUsService;
import com.honger.expo.service.ExhibitionCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExhibitionCountServiceImpl implements ExhibitionCountService{
    @Autowired
    private ExhibitionCountMapper exhibitionCountMapper;

    @Override
    public void insertOrUpdateExhibitionCount(String exhibitionId) {
        Integer integer = exhibitionCountMapper.selectExistByExhibitionId(exhibitionId);
        if(integer.equals(0)){
            ExhibitionCount ec = new ExhibitionCount();
            UUID uuid = UUID.randomUUID();
            String s = uuid.toString().replace("-","");
            ec.setCreateTime(new Date());
            ec.setUpdateTime(new Date());
            ec.setId(s);
            ec.setExhibitionId(exhibitionId);
            exhibitionCountMapper.insertExhibitonIdCount(ec);
        }else{
            exhibitionCountMapper.updateExhibitionIdCount(exhibitionId);
        }
    }

    @Override
    public Integer selectCountByExhibitionId(String exhibitionId) {
        Integer integer = exhibitionCountMapper.selectCountByExhibitonId(exhibitionId);
        return  integer;
    }
}
