package com.honger.expo.controller;

import com.honger.expo.dto.HomePageConfig;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.HomePageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private HomePageConfigService homePageConfigService;

    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseJSON categories() {
        List<CategoryListResponse> mapping = null;
        try {
            mapping = categoryService.getHomePageCategory();
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(mapping);
    }

    @ResponseBody
    @RequestMapping(value = "/exhibition", method = RequestMethod.GET)
    public ResponseJSON getHomePageExhibtion() {
        Map<String, List<ExhibitionSearchVO>> map = null;
        try {
            map = exhibitionService.getHomePage();
            Set<Map.Entry<String, List<ExhibitionSearchVO>>> entries = map.entrySet();
            for(Map.Entry<String, List<ExhibitionSearchVO>> entry : entries){
                dealWithStatus(entry.getValue());
            }
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(map);
    }

    private void doSetStatus(ExhibitionSearchVO esv, List<HomePageConfig> homePageConfigList) {
        for(HomePageConfig hc : homePageConfigList){
            if(hc!=null){
                if(!"1".equals(esv.getIsCarousel()))
                    esv.setIsCarousel(hc.getIsCarousel());
                if(!"1".equals(esv.getIsChoice()))
                    esv.setIsChoice(hc.getIsChoice());
                if(!"1".equals(esv.getIsHot()))
                    esv.setIsHot(hc.getIsHot());
            }
        }
    }

    private void dealWithStatus(List<ExhibitionSearchVO> exhibitions) {
        for(ExhibitionSearchVO esv : exhibitions){
            List<HomePageConfig> homePageConfigList = homePageConfigService.getHomePageConfig(esv.getExhibition().getId());
            doSetStatus(esv, homePageConfigList);
        }
    }
}