package com.honger.expo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honger.expo.dao.AboutUsMapper;
import com.honger.expo.dao.RegionDataMapper;
import com.honger.expo.dao.VisaMapper;
import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.dto.response.visa.VisaResponse;
import com.honger.expo.dto.vo.VisaVO;
import com.honger.expo.pojo.AboutUs;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.AboutUsService;
import com.honger.expo.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AboutUsServiceImpl implements AboutUsService{
    @Autowired
    private AboutUsMapper aboutUsMapper;

    @Override
    public List<AboutUsResponse> getAboutUs() {
        List<AboutUs> aboutUs = aboutUsMapper.getAboutUs();
        AboutUs au = aboutUs.get(0);

        List<AboutUsResponse> list = getAboutUsResponses(au,true);

        return list;
    }

    private List<AboutUsResponse> getAboutUsResponses(AboutUs au,boolean content) {
        List<AboutUsResponse> list =  new ArrayList<>();
        JSONArray objects = JSONArray.parseArray(au.getDetail());

        for(int i = 0; i < objects.size();i++){
            AboutUsResponse aur = new AboutUsResponse();
            JSONObject jsonObject = (JSONObject) objects.get(i);
            aur.setId(i+1+"");
            aur.setTitle((String) jsonObject.get("detailTitle"));
            if(content)
                aur.setContent((String) jsonObject.get("detailContent"));
            list.add(aur);
        }
        return list;
    }

    @Override
    public List<AboutUsResponse> getAboutUsByTypes() {
        List<AboutUs> aboutUsByTypes = aboutUsMapper.getAboutUs();
        AboutUs au = aboutUsByTypes.get(0);
        List<AboutUsResponse> aboutUsResponses = getAboutUsResponses(au, false);
        return aboutUsResponses;
    }
}
