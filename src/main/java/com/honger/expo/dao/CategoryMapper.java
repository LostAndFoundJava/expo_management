package com.honger.expo.dao;


import java.util.List;

import com.honger.expo.pojo.Category;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {

    Category selectByPrimaryKey(String id);

}