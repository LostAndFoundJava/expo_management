package com.honger.expo.dao;

import com.honger.expo.dto.vo.ExhibitionAndDetailVO;
import com.honger.expo.pojo.Exhibition;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ExhibitionMapper {
    //根据指定条件查询展会
    List<Exhibition> getExhibitionByCondition(@Param("country") String country,
                                              @Param("categories") String categories,
                                              @Param("maxYmdFromYM") String maxYmdFromYM,
                                              @Param("minYmdFromYM") String minYmdFromYM);

    //搜索条件
    List<Exhibition> searchExhibition(@Param("condition") String condition);

    //获得所有的展会数量
    Integer getTotalNum();

    //获取展会列表页和详情页
    List<ExhibitionAndDetailVO>  getDetial(@Param("exhibitionId") String exhibitionId);

    //首页设置
    List
}