package com.honger.expo.controller;

import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.Category;
import com.honger.expo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseJSON categories() {
        List<Category> mapping = null;
        try{
            mapping = categoryService.getAllCategory();
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(mapping);
    }
}