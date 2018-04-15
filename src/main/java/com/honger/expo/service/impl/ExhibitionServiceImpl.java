package com.honger.expo.service.impl;

import com.honger.expo.dao.CategoryMapper;
import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExhibitionServiceImpl implements ExhibitionService{
    @Autowired
    private ExhibitionMapper exhibitionMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RegionService regionService;

    @Override
    public List<Exhibition> getExhibitionByCondition(String country, String categories, String date) {
        return exhibitionMapper.getExhibitionByCondition(country,categories,date);
    }

    @Override
    public List<ExhibitionSearchVO> searchExhibition(String condition) {
        List<ExhibitionSearchVO> list = new ArrayList<>();
        List<Exhibition> exhibitions = exhibitionMapper.searchExhibition(condition);
        for(Exhibition e : exhibitions){
            ExhibitionSearchVO evo = new ExhibitionSearchVO();
            Category c = categoryService.getCategoryById(e.getCategoryId());
            RegionData rCity = regionService.getRegionCityById(e.getCity());
            RegionData rCountry = regionService.getRegionCountryById(e.getCountry());

            evo.setExhibition(e);
            evo.setCategroy(c.getName());
            evo.setCityEnName(rCity.getNameEn());
            evo.setCityName(rCity.getName());
            evo.setCityPinyin(rCity.getNamePinyin());
            evo.setCityEnName(rCountry.getNameEn());
            evo.setCityName(rCountry.getName());
            evo.setCityPinyin(rCountry.getNamePinyin());

            list.add(evo);
        }
        return list;
    }
}
