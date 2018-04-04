package com.honger.expo.service.impl;

import com.honger.expo.dao.ExhibitionCategoryDao;
import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.pojo.ExhibitionCategory;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ExhibitionCategoryDao exhibitionCategoryDao;
    @Autowired
    private RegionCacheImpl regionCache;


    @Override
    public Map<String, List<ExhibitionCategory>> getDataMapping() {
        Map<String,List<ExhibitionCategory>> mapping = new HashMap<>();
        List<ExhibitionCategory> list = exhibitionCategoryDao.queryHotCatExhibition();
        if(null != list && !list.isEmpty()){
            for(ExhibitionCategory item : list){
                if(mapping.containsKey(item.getParentId())){
                    mapping.get(item.getParentId()).add(item);
                }else {
                    List<ExhibitionCategory> init = new ArrayList<>();
                    init.add(item);
                    mapping.put(item.getParentId(),init);
                }
            }
        }
        return mapping;
    }

    @Override
    public Map<String, Map<String, Map<String, Object>>> parseCategoryData(Map<String, List<ExhibitionCategory>> mapping) {

        Map<String, Map<String, Map<String, Object>>> result = new HashMap<>();

        Set<String> keys = mapping.keySet();
        if(!keys.isEmpty()){
            for(String key : keys){
                result.put(key,parseParentCat(mapping.get(key)));
            }
        }
        return result;
    }


    private Map<String, Map<String, Object>> parseParentCat(List<ExhibitionCategory> list){
        Map<String,Map<String,Object>> result = new HashMap<>();

        Map<String,Object> categories = new HashMap<>();
        Map<String,Object> regions = new HashMap<>();

        for(ExhibitionCategory item : list){
            categories.put(item.getName(),item.getId());
            RegionData regionData = regionCache.getRegionDataByPk(item.getCountry());
            if(null != regionData){
                regions.put(regionData.getName(),item.getCountry());
            }

        }
        result.put("categories",categories);
        result.put("regions",regions);

        return result;

    }
}
