package com.honger.expo.service.impl;

import com.honger.expo.dao.FlowSrcMapper;
import com.honger.expo.pojo.FlowSrc;
import com.honger.expo.service.FlowSrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FlowSrcServiceImpl implements FlowSrcService{
    @Autowired
    private FlowSrcMapper flowSrcMapper;

    @Override
    public void insert(FlowSrc flowSrc) {
        flowSrc.setCreateTime(new Date());
        flowSrc.setUpdateTime(new Date());
        flowSrcMapper.insert(flowSrc);
    }
}
