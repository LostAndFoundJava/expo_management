package com.honger.expo.dao;

import java.util.List;

import com.honger.expo.pojo.Exhibition;
import org.apache.ibatis.annotations.Param;


public interface ExhibitionMapper {

    /**
     * 根据展会的id获取展会基本信息
     * @param id 展会的主键
     * @return 展会信息
     */
    Exhibition selectByPrimaryKey(String id);

    /**
     * 获取今天有效的展会所属的分类列表
     * @return 分类列表
     */
    List<String> selectHotCategory();

    /**
     * 获取放置到轮播图的内容
     * @return 需要显示轮播图的展会列表
     */
    List<Exhibition> selectAllShuffles();

}