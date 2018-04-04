package com.honger.expo.service.impl;

import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.ShuffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShuffleServiceImpl implements ShuffleService {

    @Autowired
    private ExhibitionMapper exhibitionMapper;

    @Override
    public List<Exhibition> getShuffles() {
        return exhibitionMapper.selectAllShuffles();
    }
}
