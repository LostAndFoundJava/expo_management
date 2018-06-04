package com.honger.expo.dao;


import com.honger.expo.pojo.AboutUs;
import com.honger.expo.pojo.Link;

import java.util.List;

public interface LinkMapper {
    //查询所有link
    List<Link> getAllLink();
}