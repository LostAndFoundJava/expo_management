package com.honger.expo.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.honger.expo.dao.RegionDataDao;
import com.honger.expo.pojo.RegionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class RegionCacheImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionCacheImpl.class);

    @Autowired
    private RegionDataDao regionDataDao;

    public static final String REGION_DATA_KEY = "regionData";

    private LoadingCache<String, ConcurrentHashMap<Integer,RegionData>> graphs = CacheBuilder.
            newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).build(
            new CacheLoader<String, ConcurrentHashMap<Integer, RegionData>>() {
                @Override
                public ConcurrentHashMap<Integer, RegionData> load(String s) {
                    return loadFromDb(s);
                }
            }
    );

    public RegionData getRegionDataByPk(Integer pk){

        Map<Integer,RegionData> regions = graphs.getUnchecked(REGION_DATA_KEY);
        if(null != regions){
            return regions.get(pk);
        }else {
            return new RegionData();
        }

    }


    private ConcurrentHashMap<Integer,RegionData> loadFromDb(String s){

        ConcurrentHashMap<Integer,RegionData> result = new ConcurrentHashMap<>();

        try{
            List<RegionData> regions = regionDataDao.selectAll();
            if(null != regions){
                for(RegionData region : regions){
                    result.put(region.getId(),region);
                }
            }
        }catch (Exception e){
            LOGGER.warn("加载地区缓存信息DB异常",e);
        }
        return result;
    }
}
