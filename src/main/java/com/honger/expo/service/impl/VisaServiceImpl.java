package com.honger.expo.service.impl;

import com.honger.expo.dao.CategoryMapper;
import com.honger.expo.dao.RegionDataMapper;
import com.honger.expo.dao.VisaMapper;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.visa.VisaResponse;
import com.honger.expo.dto.vo.CategoryExhibitionRegionVO;
import com.honger.expo.dto.vo.VisaVO;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.RegionService;
import com.honger.expo.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VisaServiceImpl implements VisaService {
    @Autowired
    private RegionDataMapper regionDataMapper;

    @Autowired
    private VisaMapper visaMapper;

    @Override
    public List<RegionData> getRegionCountryByContinent(String continent) {
        return regionDataMapper.getRegionCountryByContinent(continent);
    }

    @Override
    public VisaResponse getVisaByCountryId(String countryId) {
        List<VisaVO> visaByCountryId = visaMapper.getVisaByCountryId(countryId);
        VisaResponse vr = new VisaResponse();

        RegionData country = new RegionData();
        RegionData contitent = new RegionData();
        List<Map<String,String>> list =  new ArrayList<>();

        VisaVO vv = visaByCountryId.get(0);

        country.setId(new Integer(vv.getCityId()));
        country.setName(vv.getCityName());
        country.setNameEn(vv.getCityNameEn());
        country.setNamePinyin(vv.getCityNamePinyin());

        contitent.setId(new Integer(vv.getcId()));
        contitent.setNameEn(vv.getcNameEn());
        contitent.setName(vv.getcName());
        contitent.setNamePinyin(vv.getCityNamePinyin());
        for(VisaVO vvo : visaByCountryId){
            if(vvo.getFileUrl()!=null){
                Map<String,String> map = new HashMap<>();
                map.put("visaUrl",vvo.getFileUrl());
                map.put("visaName",vvo.getFileName());
                map.put("visaFileId",vvo.getfId());
                list.add(map);
            }
        }
        vr.setCreateTime(vv.getCreateTime());
        vr.setUpdateTime(vv.getUpdateTime());
        vr.setVisaInfo(vv.getVisaInfo());

        vr.setContinent(contitent);
        vr.setCountry(country);
        vr.setVisa(list);

        return vr;
    }
}
