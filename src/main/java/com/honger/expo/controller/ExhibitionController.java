package com.honger.expo.controller;


import com.github.pagehelper.PageHelper;
import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.dto.vo.Page;
import com.honger.expo.myexception.MyDateFormatException;
import com.honger.expo.service.ExhibitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
