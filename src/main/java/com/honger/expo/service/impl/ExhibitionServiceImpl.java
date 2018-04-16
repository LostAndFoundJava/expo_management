package com.honger.expo.service.impl;

import com.honger.expo.dao.CategoryMapper;
import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.vo.ExhibitionAndDetailVO;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.*;
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

    @Override
    public ExhibitionDetailResponse getDetail(String exhibitionId) {
        List<ExhibitionAndDetailVO> detial = exhibitionMapper.getDetial(exhibitionId);
        ExhibitionAndDetailVO exhibitionAndDetailVO = detial.get(0);
        ExhibitionDetailResponse edr = new ExhibitionDetailResponse();

        //转换展会列表页，只有一个
        List<Exhibition> exhibitions = new ArrayList<>();
        Exhibition e = new Exhibition();
        e.setId(exhibitionAndDetailVO.getId());
        e.setTitle(exhibitionAndDetailVO.getTitle());
        e.setCategoryId(exhibitionAndDetailVO.getCategoryId());
        e.setCity(exhibitionAndDetailVO.getCity());
        e.setCountry(exhibitionAndDetailVO.getCountry());
        e.setThumbnail(exhibitionAndDetailVO.getThumbnail());
        e.setTag(exhibitionAndDetailVO.getTag());
        e.setSubtitle(exhibitionAndDetailVO.getSubtitle());
        e.setHot(exhibitionAndDetailVO.getHot());
        e.setHasCarousel(exhibitionAndDetailVO.getHasCarousel());
        e.setStartTime(exhibitionAndDetailVO.getStartTime());

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
