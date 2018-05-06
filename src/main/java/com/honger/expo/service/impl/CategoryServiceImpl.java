package com.honger.expo.service.impl;

import com.honger.expo.dao.CategoryMapper;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.vo.CategoryExhibitionRegionVO;
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
        List<CategoryExhibitionRegionVO> homePageCategory = categoryMapper.getHomePageCategory();
        List<Category> allCategory = categoryMapper.getAllCategory();
        getNoExhibitionCategory(homePageCategory,allCategory);
        List<CategoryListResponse> list = transform(homePageCategory);
        return list;
    }

    private void getNoExhibitionCategory(List<CategoryExhibitionRegionVO> homePageCategory, List<Category> allCategory) {
        List<Category> notInCategroyExhibition = new ArrayList<>();
        boolean flag = false;
        for(Category c : allCategory){
            for(CategoryExhibitionRegionVO cer : homePageCategory){
                if(c.getId().equals(cer.getId())){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                notInCategroyExhibition.add(c);
            }
            flag = false;
        }

        for(Category c : notInCategroyExhibition){
            CategoryExhibitionRegionVO cer = new CategoryExhibitionRegionVO();
            cer.setId(c.getId());
            cer.setName(c.getName());
            homePageCategory.add(cer);
        }
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

    private List<CategoryListResponse> transform(List<CategoryExhibitionRegionVO> homePageCategory) {
        List<CategoryListResponse> list = new ArrayList<CategoryListResponse>();
        //数据库返回的数据
        for(CategoryExhibitionRegionVO i :homePageCategory){
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
            if(!flag){
                CategoryListResponse categoryListResponse = new CategoryListResponse();
                categoryListResponse.setId(i.getId());
                categoryListResponse.setSubCategory("");
                categoryListResponse.setTitle(i.getName());
                RegionData r = new RegionData();
                Set<RegionData> country = new HashSet<>();
                if(i.getrId()!=null){
                    r.setId(i.getrId());
                    r.setName(i.getrName());
                    r.setNamePinyin(i.getNamePinyin());
                    r.setNameEn(i.getNameEn());
                    country.add(r);
                }
                categoryListResponse.setCountry(country);
                list.add(categoryListResponse);
            }

        }
        return list;
    }
}
