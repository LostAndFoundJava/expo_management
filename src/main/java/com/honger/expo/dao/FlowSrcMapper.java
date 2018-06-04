package com.honger.expo.dao;

import com.honger.expo.pojo.FlowSrc;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chenjian on 2018/4/16.
 */
public interface FlowSrcMapper {
    Integer insert(FlowSrc flowSrc);

    void update(FlowSrc flowSrc);

    String isMobileUnique(@Param("mobileNo") String mobileNo,@Param("exhibitionId") String exhibitionId);
}
