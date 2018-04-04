package com.honger.expo.service;

import com.honger.expo.pojo.ExhibitionCategory;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    /**
     * 从数据库获取门类信息，并且按照主类别分类
     * @return 按照主类别分类的门类信息
     */
    Map<String,List<ExhibitionCategory>> getDataMapping();

    /**
     * 将获取的门类mapping信息解析成前端需要的内容
     * @param mapping 需要解析的内容
     * @return 前端需要的内容
     */
    Map<String,Map<String,Map<String,Object>>> parseCategoryData(Map<String,List<ExhibitionCategory>> mapping);
}
