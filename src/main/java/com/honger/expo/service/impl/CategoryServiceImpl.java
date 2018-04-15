package com.honger.expo.service.impl;

import com.honger.expo.dao.CategoryMapper;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.vo.CategoryExhibitonRegionVO;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryListResponse> getHomePageCategory() {
        List<CategoryExhibitonRegionVO> homePageCategory = categoryMapper.getHomePageCategory();
        List<CategoryListResponse> list = transform(homePageCategory);
        return list;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public String getCategoryIdByName(String category) {
        return categoryMapper.getCategoryIdByName(category);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return categoryMapper.getCategoryById(categoryId);
    }

    private List<CategoryListResponse> transform(List<CategoryExhibitonRegionVO> homePageCategory) {
        List<CategoryListResponse> list = new ArrayList<CategoryListResponse>();
        //数据库返回的数据
        for(CategoryExhibitonRegionVO i :homePageCategory){
            boolean flag = false;
            //返回前端的数据
            for(CategoryListResponse j : list){
                if(j.getId().equals(i.getId())){
                    //多次添加
                    flag = true;
                    RegionData r = new RegionData();
                    r.setId(i.getrId());
                    r.setName(i.getrName());
                    r.setNamePinyin(i.getNamePinyin());
                    r.setNameEn(i.getNameEn());
                    j.getCountry().add(r);
                    break;
                }
            }

            //首次添加
            if(flag == false){
                CategoryListResponse categoryListResponse = new CategoryListResponse();
                categoryListResponse.setId(i.getId());
                categoryListResponse.setSubCategory("");
                categoryListResponse.setTitle(i.getName());
                RegionData r = new RegionData();
                r.setId(i.getrId());
                r.setName(i.getrName());
                r.setNamePinyin(i.getNamePinyin());
                r.setNameEn(i.getNameEn());
                Set<RegionData> country = new HashSet<>();
                country.add(r);
                categoryListResponse.setCountry(country);
                list.add(categoryListResponse);
            }

        }
        return list;
    }
}
