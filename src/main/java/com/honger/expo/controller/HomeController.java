package com.honger.expo.controller;

import com.honger.expo.dto.HomePageConfig;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Link;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.HomePageConfigService;
import com.honger.expo.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping(value = "/api/home")
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
            ConcurrentHashMap<String, Object> concurrentHashMap = CacheUtils.getCacheSingleton().getConcurrentHashMap();
            boolean b = concurrentHashMap.containsKey("home.categories");
            if(b){
                mapping = (List<CategoryListResponse>)concurrentHashMap.get("home.categories");
            }else{
                mapping = categoryService.getHomePageCategory();
                concurrentHashMap.put("home.categories",mapping);
            }
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
            CacheUtils cacheSingleton = CacheUtils.getCacheSingleton();
            ConcurrentHashMap<String, Object> concurrentHashMap = cacheSingleton.getConcurrentHashMap();
            boolean link = concurrentHashMap.containsKey("home.exhibition");
            if(link){
                map = (Map<String, List<ExhibitionSearchVO>>)concurrentHashMap.get("home.exhibition");
//                System.out.println("===from cache");
            } else{
                map = exhibitionService.getHomePage();
                Set<Map.Entry<String, List<ExhibitionSearchVO>>> entries = map.entrySet();
                for(Map.Entry<String, List<ExhibitionSearchVO>> entry : entries){
                    dealWithStatus(entry.getValue());
                }
                concurrentHashMap.put("home.exhibition",map);
//                System.out.println("===from db");
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