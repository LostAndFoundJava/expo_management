package com.honger.expo.controller;


import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ExhibitionService exhibitionService;

    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseJSON categories() {
        List<CategoryListResponse> mapping = null;
        try {
            mapping = categoryService.getHomePageCategory();
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(mapping);
    }

    @ResponseBody
    @RequestMapping(value = "/exhibition", method = RequestMethod.GET)
    public ResponseJSON getHomePageExhibtion() {
        Map<String, List<ExhibitionSearchVO>> map = null;
        try {
            map = exhibitionService.getHomePage();
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(map);
    }
}