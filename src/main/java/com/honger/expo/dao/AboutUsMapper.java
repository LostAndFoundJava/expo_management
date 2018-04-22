package com.honger.expo.dao;


import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.pojo.AboutUs;

import java.util.List;

public interface AboutUsMapper {
    //查询aboutus
    List<AboutUs> getAboutUs();
}