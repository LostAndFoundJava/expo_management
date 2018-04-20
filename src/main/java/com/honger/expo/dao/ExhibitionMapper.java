package com.honger.expo.dao;

import com.honger.expo.dto.vo.ExhibitionAndDetailVO;
import com.honger.expo.dto.vo.ExhibitionHomePage;
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

    //获得所有的筛选条件下展会数量
    Integer getTotalNumByConditon(@Param("country") String country,
                                  @Param("categories") String categories,
                                  @Param("maxYmdFromYM") String maxYmdFromYM,
                                  @Param("minYmdFromYM") String minYmdFromYM);

    //获取展会列表页和详情页
    List<ExhibitionAndDetailVO>  getDetial(@Param("exhibitionId") String exhibitionId);

    //首页设置
    List<ExhibitionHomePage> getHomePage();

    //根据搜索条件查询展会数量
    Integer getTotalNumBySearch(@Param("query") String query);

    //查询行业精选展会
    List<Exhibition> getExhibitonByIsChoice();
}