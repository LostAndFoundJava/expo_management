package com.honger.expo.controller;

import com.github.pagehelper.PageHelper;
import com.honger.expo.annotation.CountAnnotation;
import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.vo.ClickCountExhibition;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.dto.vo.Page;
import com.honger.expo.myexception.MyDateFormatException;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.ClickCount;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.ExhibitionCountService;
import com.honger.expo.service.ExhibitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/expos")
@Slf4j
public class ExhibitionController {
    final static private int pageSize = 3;
    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ExhibitionCountService exhibitionCountService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseJSON getExhibitionByCondition(
            @RequestParam(value = "country", required = false, defaultValue = "") String country,
            @RequestParam(value = "categories", required = false, defaultValue = "") String categories,
            @RequestParam(value = "date", required = false, defaultValue = "") String date,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        List<ExhibitionSearchVO> exhibitions = null;
        Page<List<ExhibitionSearchVO>> rPage = null;
        try {
            PageHelper.startPage(Integer.valueOf(page), pageSize);
            exhibitions = exhibitionService.getExhibitionByCondition(country, categories, date);
            Integer totalNum = exhibitionService.getTotalNumByConditon(country, categories, date);
            rPage = new Page<List<ExhibitionSearchVO>>();
            rPage.setContent(exhibitions);
            rPage.setPageSize(pageSize);
            rPage.setTotalNum(totalNum);
            rPage.setPageNum(Integer.valueOf(page));
            if (totalNum - pageSize * Integer.valueOf(page) <= 0) {
                rPage.setLast(true);
            } else {
                rPage.setLast(false);
            }
        } catch (MyDateFormatException e) {
            log.error(e.getMessage());
            return ResponseJSON.error(e.getMessage());
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(rPage);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseJSON searchExhibitionByCondition(
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        List<ExhibitionSearchVO> exhibitions = null;
        Page<List<ExhibitionSearchVO>> rPage = null;
        try {
            if (query.equals(""))
                return ResponseJSON.error("no input search");

            PageHelper.startPage(Integer.valueOf(page), pageSize);
            exhibitions = exhibitionService.searchExhibition(query);
            Integer totalNum = exhibitionService.getTotalNumBySearch(query);
            rPage = new Page<List<ExhibitionSearchVO>>();
            rPage.setContent(exhibitions);
            rPage.setPageSize(pageSize);
            rPage.setTotalNum(totalNum);
            rPage.setPageNum(Integer.valueOf(page));
            if (totalNum - pageSize * Integer.valueOf(page) <= 0) {
                rPage.setLast(true);
            } else {
                rPage.setLast(false);
            }
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(rPage);
    }

    @CountAnnotation
    @ResponseBody
    @RequestMapping(value = "/detail/{exhibitionId}", method = RequestMethod.GET)
    public ResponseJSON getExhibitonDetail(@PathVariable("exhibitionId") String exhibitionId) {
        ExhibitionDetailResponse detail = null;
        try {
            detail = exhibitionService.getDetail(exhibitionId);
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(detail);
    }

    @ResponseBody
    @RequestMapping(value = "/count/{exhibitionId}", method = RequestMethod.GET)
    public ResponseJSON getExhibitionCount(@PathVariable("exhibitionId") String exhibitionId) {
        HashMap<String,Integer> num = new HashMap<>();
        try {
            num.put("count",exhibitionCountService.selectCountByExhibitionId(exhibitionId));
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(num);
    }

    @ResponseBody
    @RequestMapping(value = "/clicks", method = RequestMethod.GET)
    public ResponseJSON getTopClickExhibiton(@RequestParam("top") String top) {
        List<ClickCountExhibition> list = null;
        if(top==null || top.trim().equals(""))
            top = "0";
        try {
            list = exhibitionCountService.getTopClickExhibiton(top);
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(list);
    }

    @ResponseBody
    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    public ResponseJSON getHotAndCarsoulExhibition() {
        Map<String, List<ExhibitionSearchVO>> map = null;
        Page<Set<ExhibitionSearchVO>> hotPage = null;
        try {
            map = exhibitionService.getHomePage();
            map.remove("choice");
            hotPage = addSearchToPage(map);
            hotPage.setLast(true);
        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(hotPage);
    }

    @ResponseBody
    @RequestMapping(value = "/choices", method = RequestMethod.GET)
    public ResponseJSON getChoiceExhibition() {
        Map<String, List<ExhibitionSearchVO>> map = null;
        Page<Set<ExhibitionSearchVO>> hotPage = null;

        try {
            map = exhibitionService.getHomePage();
            map.remove("hot");
            map.remove("carousal");
            hotPage = addSearchToPage(map);
            hotPage.setLast(true);



        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(hotPage);
    }

    private Page<Set<ExhibitionSearchVO>> addSearchToPage(Map<String, List<ExhibitionSearchVO>> map) {
        Set<ExhibitionSearchVO> exhibitionSearchVOS = new HashSet<>();
        for (Map.Entry<String, List<ExhibitionSearchVO>> m : map.entrySet()) {
            exhibitionSearchVOS.addAll(m.getValue());

        }
        Page<Set<ExhibitionSearchVO>> page = new Page<>();
        page.setContent(exhibitionSearchVOS);
        page.setPageNum(1);
        page.setTotalNum(exhibitionSearchVOS.size());
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/hotExpos", method = RequestMethod.GET)
    public ResponseJSON getCategoryHotExhibition(
            @RequestParam(value = "categoryId", required = false, defaultValue = "") String categoryId) {
        Map<String, List<ExhibitionSearchVO>> map = new HashMap<>();
        int totalSize = 3;
        try {
            Map<String, List<ExhibitionSearchVO>> stringListMap = exhibitionService.getHomePage();

            List<ExhibitionSearchVO> carousalList = new ArrayList<>();
            List<ExhibitionSearchVO> hotList = new ArrayList<>();
            List<ExhibitionSearchVO> choiceList = new ArrayList<>();


            for (Map.Entry<String, List<ExhibitionSearchVO>> entry : stringListMap.entrySet()){
                if(entry.getKey().equals("carousal"))
                    carousalList.addAll(entry.getValue());
                if(entry.getKey().equals("hot"))
                    hotList.addAll(entry.getValue());
                if(entry.getKey().equals("choice"))
                    choiceList.addAll(entry.getValue());
            }

            Category category = categoryService.getCategoryById(categoryId);
            category.setId(categoryId);
            int carousalSize = 0;
            int hotSize = 0;
            int choiceSize = 0;

            carousalSize = getNeedSize(carousalList, category, carousalSize);

            hotSize = getNeedSize(hotList, category, hotSize);

            choiceSize = getNeedSize(choiceList, category, choiceSize);

            int first = totalSize - carousalSize;
            if(first > 0){
                map.put("carousal",carousalList);
                int second = first - hotSize;
                if(second > 0){
                    map.put("hot",hotList);
                    int third = second - choiceSize;
                    if(third > 0){
                        map.put("choice",choiceList);
                    }else{
                        List<ExhibitionSearchVO> tmpChoiceList = new ArrayList<>();
                        for(int i = 0;i < choiceList.size();i++){
                            if(i == second)
                                break;
                            tmpChoiceList.add(choiceList.get(i));
                        }
                        map.put("choice",tmpChoiceList);
                    }
                }else{
                    List<ExhibitionSearchVO> tmpHotList = new ArrayList<>();
                    for(int i = 0;i < hotList.size();i++){
                        if(i == first)
                            break;
                        tmpHotList.add(hotList.get(i));
                    }
                    map.put("hot",tmpHotList);
                }
            }else{
                List<ExhibitionSearchVO> tmpCarousalList = new ArrayList<>();
                for(int i = 0;i < carousalList.size();i++){
                    if(i == totalSize)
                        break;
                    tmpCarousalList.add(carousalList.get(i));
                }
                map.put("carousal",tmpCarousalList);
            }

        } catch (Exception e) {
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(map);
    }

    private int getNeedSize(List<ExhibitionSearchVO> choiceList, Category category, int choiceSize) {
        ListIterator<ExhibitionSearchVO> exhibitionSearchVOListIterator2 = choiceList.listIterator();
        while(exhibitionSearchVOListIterator2.hasNext()) {
            ExhibitionSearchVO next = exhibitionSearchVOListIterator2.next();
            if (next.getExhibition().getCategoryId().equals(category.getId())) {
                choiceSize++;
            }else {
                exhibitionSearchVOListIterator2.remove();
            }
        }
        return choiceSize;
    }
}
