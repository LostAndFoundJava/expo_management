package com.honger.expo.controller;


import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.RegionService;
import com.honger.expo.service.impl.CategoryServiceImpl;
import com.honger.expo.service.impl.ShuffleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/countries")
public class CountryController {

    @Autowired
    private RegionService regionService;


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseJSON categories(@RequestParam(value = "continent", required = false, defaultValue = "")
                                               String continent) {
        String start =  "controller=="+System.currentTimeMillis();

        List<RegionData> regionDatas = null;
        try{
            regionDatas = regionService.selectCondition(continent);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        String end =  "controller=="+System.currentTimeMillis();
        return ResponseJSON.ok(regionDatas);
    }

}