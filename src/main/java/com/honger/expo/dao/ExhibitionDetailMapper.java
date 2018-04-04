package com.honger.expo.dao;

import java.util.List;

import com.honger.expo.pojo.ExhibitionDetail;
import com.honger.expo.pojo.ExhibitionDetailWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface ExhibitionDetailMapper {

    ExhibitionDetailWithBLOBs selectByPrimaryKey(String id);
}