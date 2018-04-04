package com.honger.expo.dao;

import com.honger.expo.pojo.ExhibitionCategory;

import java.util.List;

/**
 * 分类及展会联合查询
 */
public interface ExhibitionCategoryDao {

    List<ExhibitionCategory> queryHotCatExhibition();

}
