package com.honger.expo.dao;


import com.honger.expo.dto.vo.VisaVO;
import com.honger.expo.pojo.FileResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileResourceMapper {
    //查询国家下面的签证
    FileResource getFileResourceById(@Param("id") String id);
}