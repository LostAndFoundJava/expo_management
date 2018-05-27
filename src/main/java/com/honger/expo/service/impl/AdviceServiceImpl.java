package com.honger.expo.service.impl;

import com.honger.expo.dao.AdviceMapper;
import com.honger.expo.pojo.Advice;
import com.honger.expo.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AdviceServiceImpl implements AdviceService {
    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public void insert(Advice advice) {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replace("-","");
        advice.setId(s);
        advice.setCreateTime(new Date());
        advice.setUpdateTime(new Date());
        adviceMapper.insert(advice);
    }
}
