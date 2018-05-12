package com.honger.expo.service.impl;

import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.vo.ExhibitionAndDetailVO;
import com.honger.expo.dto.vo.ExhibitionHomePage;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.*;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.RegionService;
import com.honger.expo.utils.BeanReflectUtil;
import com.honger.expo.utils.DateTransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            maxYmdFromYM = DateTransformUtil.getMaxYmdFromYM(date);
            minYmdFromYM = DateTransformUtil.getMinYmdFromYM(date);
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
    public Integer getTotalNumByConditon(String country,String categories,String  date) {
        String maxYmdFromYM = "";
        String minYmdFromYM = "";

        if(!date.equals("")){
            maxYmdFromYM = DateTransformUtil.getMaxYmdFromYM(date);
            minYmdFromYM = DateTransformUtil.getMinYmdFromYM(date);
        }
        return exhibitionMapper.getTotalNumByConditon(country, categories, maxYmdFromYM,minYmdFromYM);
    }

    @Override
    public ExhibitionDetailResponse getDetail(String exhibitionId) throws InvocationTargetException, IllegalAccessException {
        List<ExhibitionAndDetailVO> detial = exhibitionMapper.getDetial(exhibitionId);
        ExhibitionAndDetailVO exhibitionAndDetailVO = detial.get(0);
        ExhibitionDetailResponse edr = new ExhibitionDetailResponse();

        //转换展会列表页，只有一个
        List<Exhibition> exhibitions = new ArrayList<>();
        Exhibition e = new Exhibition();

        BeanReflectUtil.beanSet(exhibitionAndDetailVO,e);

        exhibitions.add(e);

        //添加展会详情页面，只有一个
        ExhibitionDetailWithBLOBs exhibitionDetail = new ExhibitionDetailWithBLOBs();
        exhibitionDetail.setBriefInfo(exhibitionAndDetailVO.getBriefInfo());
        exhibitionDetail.setDescription(exhibitionAndDetailVO.getDescription());

        //处理多个关联的图片列表
        List<FileResource> fr = new ArrayList<>();

        for(ExhibitionAndDetailVO eav : detial){
            FileResource fileResource = new FileResource();
            fileResource.setFileName(eav.getFileName());
            fileResource.setFileUrl(eav.getFileUrl());
            fr.add(fileResource);
        }

        List<ExhibitionSearchVO> exhibitionSearchVOS = getExhibitionSearchVOS(exhibitions);
        edr.setExhibition(exhibitionSearchVOS.get(0));
        edr.setExhibitionDetail(exhibitionDetail);
        edr.setFileResource(fr);

        return edr;
    }

    @Override
    public Map<String,List<ExhibitionSearchVO>> getHomePage() throws InvocationTargetException, IllegalAccessException {
        Map<String,List<ExhibitionSearchVO>> hp = new HashMap<>();
        List<ExhibitionHomePage> homePage = exhibitionMapper.getHomePage();
        List<Exhibition> isHot = new ArrayList<>();
        List<Exhibition> isChoice = new ArrayList<>();
        List<Exhibition> isCarousal = new ArrayList<>();
        for(ExhibitionHomePage eh : homePage){
            Exhibition e = new Exhibition();

            BeanReflectUtil.beanSet(eh,e);

            if(eh.getIsHot().equals("1")){
                isHot.add(e);
            }
            if(eh.getIsChoice().equals("1")){
                isChoice.add(e);
            }
            if(eh.getIsCarousel().equals("1")){
                isCarousal.add(e);
            }
        }
        List<ExhibitionSearchVO> exhibitionSearchVOS = getExhibitionSearchVOS(isHot);
        List<ExhibitionSearchVO> exhibitionSearchVOS1 = getExhibitionSearchVOS(isChoice);
        List<ExhibitionSearchVO> exhibitionSearchVOS2 = getExhibitionSearchVOS(isCarousal);
        hp.put("hot",exhibitionSearchVOS);
        hp.put("choice",exhibitionSearchVOS1);
        hp.put("carousal",exhibitionSearchVOS2);

        return hp;
    }

    @Override
    public Integer getTotalNumBySearch(String query) {
        return exhibitionMapper.getTotalNumBySearch(query);
    }

    @Override
    public Exhibition getExhibitionById(String exhibitionId) {
        return exhibitionMapper.selectExhibtionById(exhibitionId);
    }

    public List<ExhibitionSearchVO> getExhibitionSearchVOS(List<Exhibition> exhibitions) {
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
