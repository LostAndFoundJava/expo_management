package com.honger.expo.controller;


import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.ExhibitionCategory;
import com.honger.expo.service.impl.CategoryServiceImpl;
import com.honger.expo.service.impl.ShuffleServiceImpl;
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
    private CategoryServiceImpl categoryService;
    @Autowired
    private ShuffleServiceImpl shuffleService;

    @ResponseBody
    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, Object>>> categories(){
        Map<String, List<ExhibitionCategory>> mapping = categoryService.getDataMapping();

        return categoryService.parseCategoryData(mapping);
    }

    @ResponseBody
    @RequestMapping(value = "/shuffle", method = RequestMethod.GET)
    public List<Exhibition> shuffle(){
        return shuffleService.getShuffles();
    }
}
