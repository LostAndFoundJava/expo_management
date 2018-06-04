package com.honger.expo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honger.expo.dao.AboutUsMapper;
import com.honger.expo.dao.LinkMapper;
import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.pojo.AboutUs;
import com.honger.expo.pojo.Link;
import com.honger.expo.service.AboutUsService;
import com.honger.expo.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService{
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> getAllLink() {
        return linkMapper.getAllLink();
    }
}
