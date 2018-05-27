package com.honger.expo.service.impl;

import com.honger.expo.dao.ClickCountMapper;
import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dao.NewsMapper;
import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.vo.ClickCountVO;
import com.honger.expo.dto.vo.NewsCategoryVO;
import com.honger.expo.pojo.ClickCount;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.ClickCountService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.utils.CountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClickCountServiceImpl implements ClickCountService {
    @Autowired
    private ClickCountMapper clickCountMapper;

//    @Autowired
//    private ExhibitionMapper exhibitionMapper;
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ExhibitionService exhibitionService;

    @Override
    public void insertOrUpdateExhibitionCount(String exhibitionId,Integer type) throws InvocationTargetException, IllegalAccessException {
        Integer integer = clickCountMapper.selectExistByExhibitionId(exhibitionId,type);
        ExhibitionDetailResponse detail = exhibitionService.getDetail(exhibitionId);
        if(detail == null){
            return;
        }
        if(integer.equals(0)){
            ClickCount ec = new ClickCount();
            UUID uuid = UUID.randomUUID();
            String s = uuid.toString().replace("-","");
            ec.setCreateTime(new Date());
            ec.setUpdateTime(new Date());
            ec.setId(s);
            ec.setClickedId(exhibitionId);
            ec.setClickType(type);
            clickCountMapper.insertExhibitionIdCount(ec);
        }else{
            clickCountMapper.updateExhibitionIdCount(exhibitionId,type);
        }
    }

    @Override
    public Integer selectCountByExhibitionId(String exhibitionId) {
        Integer integer = clickCountMapper.selectCountByExhibitionId(exhibitionId);
        return  integer;
    }

    @Override
    public List<ClickCountVO> getTopClickExhibition(String top, CountType countType) {
        List<ClickCount> topClickExhibition = clickCountMapper.getTopClickExhibition(new Integer(top),
                new Integer(countType.getType()));


        List<ClickCountVO> list = new ArrayList<>();
        for(ClickCount cc : topClickExhibition){
            ClickCountVO clickCountVO = new ClickCountVO();
            if(countType.getType().equals("0")) {
                ExhibitionMapper exhibitionMapper = (ExhibitionMapper) applicationContext.
                        getBean("exhibitionMapper");
                Exhibition exhibition = exhibitionMapper.selectExhibtionById(cc.getClickedId());
                clickCountVO.setClicked(exhibition);
            }else if((countType.getType().equals("1"))){
                NewsMapper newsMapper = (NewsMapper) applicationContext.
                        getBean("newsMapper");
                NewsCategoryVO newsById = newsMapper.getNewsById(cc.getClickedId());
                clickCountVO.setClicked(newsById);
            }
            clickCountVO.setClickCount(cc);
            list.add(clickCountVO);
        }
        return list;
    }
}
