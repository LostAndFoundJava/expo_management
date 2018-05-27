package com.honger.expo.dao;

import com.honger.expo.pojo.Advice;
import com.honger.expo.pojo.FlowSrc;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chenjian on 2018/4/16.
 */
public interface AdviceMapper {
    void insert(Advice advice);
//    String isMobileUnique(@Param("mobileNo") String mobileNo);
}
