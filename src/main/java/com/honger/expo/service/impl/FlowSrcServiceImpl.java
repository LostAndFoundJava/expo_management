package com.honger.expo.service.impl;

import com.honger.expo.dao.FlowSrcMapper;
import com.honger.expo.pojo.FlowSrc;
import com.honger.expo.service.FlowSrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class FlowSrcServiceImpl implements FlowSrcService{
    @Autowired
    private FlowSrcMapper flowSrcMapper;

    @Override
    public String insert(FlowSrc flowSrc) {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replace("-","");
        flowSrc.setId(s);
        flowSrc.setCreateTime(new Date());
        flowSrc.setUpdateTime(new Date());
        flowSrc.setSrcType("0");
        Integer insert = flowSrcMapper.insert(flowSrc);
        if(insert.equals(1))
            return s;
        else
            return "";
    }

    @Override
    public void update(FlowSrc flowSrc) {
        flowSrc.setUpdateTime(new Date());
        flowSrcMapper.update(flowSrc);
    }
}
