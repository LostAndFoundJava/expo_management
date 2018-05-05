package com.honger.expo.service.impl;

import com.honger.expo.dao.ExhibitionCountMapper;
import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dto.vo.ClickCountExhibition;
import com.honger.expo.pojo.ClickCount;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.ExhibitionCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExhibitionCountServiceImpl implements ExhibitionCountService{
    @Autowired
    private ExhibitionCountMapper exhibitionCountMapper;

    @Autowired
    private ExhibitionMapper exhibitionMapper;

    @Override
    public void insertOrUpdateExhibitionCount(String exhibitionId) {
        Integer integer = exhibitionCountMapper.selectExistByExhibitionId(exhibitionId);
        if(integer.equals(0)){
            ClickCount ec = new ClickCount();
            UUID uuid = UUID.randomUUID();
            String s = uuid.toString().replace("-","");
            ec.setCreateTime(new Date());
            ec.setUpdateTime(new Date());
            ec.setId(s);
            ec.setClickedId(exhibitionId);
            ec.setClickType(0);
            exhibitionCountMapper.insertExhibitonIdCount(ec);
        }else{
            exhibitionCountMapper.updateExhibitionIdCount(exhibitionId);
        }
    }

    @Override
    public Integer selectCountByExhibitionId(String exhibitionId) {
        Integer integer = exhibitionCountMapper.selectCountByExhibitonId(exhibitionId);
        return  integer;
    }

    @Override
    public List<ClickCountExhibition> getTopClickExhibiton(String top) {
        List<ClickCount> topClickExhibiton = exhibitionCountMapper.getTopClickExhibiton(new Integer(top));

        List<ClickCountExhibition> list = new ArrayList<>();
        for(ClickCount cc : topClickExhibiton){
            ClickCountExhibition clickCountExhibition = new ClickCountExhibition();
            Exhibition exhibition = exhibitionMapper.selectExhibtionById(cc.getClickedId());
            clickCountExhibition.setClickCount(cc);
            clickCountExhibition.setExhibition(exhibition);
            list.add(clickCountExhibition);
        }
        return list;
    }
}
