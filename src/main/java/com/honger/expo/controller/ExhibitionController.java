package com.honger.expo.controller;


import com.github.pagehelper.PageHelper;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.RegionService;
import com.honger.expo.service.impl.CategoryServiceImpl;
import com.honger.expo.service.impl.ShuffleServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/expos")
public class ExhibitionController {
    final static private int pageSize = 1;

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseJSON getExhibitionByCondition(
            @RequestParam(value = "country",required = false,defaultValue = "") String country,
            @RequestParam(value = "categories",required = false,defaultValue = "")String categories,
            @RequestParam(value = "date",required = false,defaultValue = "")String date,
            @RequestParam(value = "page",required = false,defaultValue = "")String page) {
        List<Exhibition> exhibitionByCondition = null;
        try{
            if(!country.equals(""))
                country = regionService.getRegionIdByCountryName(country);

            if(!categories.equals(""))
                categories = categoryService.getCategoryIdByName(categories);

            PageHelper.startPage(Integer.valueOf(page),pageSize);
            exhibitionByCondition = exhibitionService.getExhibitionByCondition(country, categories, date);
        }catch (Exception e){
            return ResponseJSON.error();
        }

        return ResponseJSON.ok(exhibitionByCondition);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseJSON searchExhibitionByCondition(
            @RequestParam(value = "query",required = false,defaultValue = "") String query){
        List<ExhibitionSearchVO> exhibitions = null;
        try{
            if(query.equals(""))
                return ResponseJSON.error("no input search");

            exhibitions = exhibitionService.searchExhibition(query);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(exhibitions);
    }
}