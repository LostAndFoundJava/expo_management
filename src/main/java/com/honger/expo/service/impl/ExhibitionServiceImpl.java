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
import com.honger.expo.utils.DateTransform;
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
    public List<ExhibitionSearchVO> getExhibitionByCondition(String country, String categories, String date) {
        String maxYmdFromYM = "";
        String minYmdFromYM = "";
        if(!date.equals("")){
            maxYmdFromYM = DateTransform.getMaxYmdFromYM(date);
            minYmdFromYM = DateTransform.getMinYmdFromYM(date);
        }
        List<Exhibition> exhibitionByCondition = exhibitionMapper.getExhibitionByCondition(country, categories, maxYmdFromYM,minYmdFromYM);
        return getExhibitionSearchVOS(exhibitionByCondition);
    }

    @Override
    public List<ExhibitionSearchVO> searchExhibition(String condition) {
        List<Exhibition> exhibitions = exhibitionMapper.searchExhibition(condition);
        return getExhibitionSearchVOS(exhibitions);
    }

    @Override
    public Integer getTotalNum() {
        return exhibitionMapper.getTotalNum();
    }

    private List<ExhibitionSearchVO> getExhibitionSearchVOS(List<Exhibition> exhibitions) {
        List<ExhibitionSearchVO> list = new ArrayList<>();
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
            evo.setCountryEnName(rCountry.getNameEn());
            evo.setCountryName(rCountry.getName());
            evo.setCountryPinyin(rCountry.getNamePinyin());

            list.add(evo);
        }
        return list;
    }
}
