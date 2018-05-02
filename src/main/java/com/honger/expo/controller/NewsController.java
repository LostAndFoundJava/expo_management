package com.honger.expo.controller;

import com.github.pagehelper.PageHelper;
import com.honger.expo.dto.response.news.NewsCategoryResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.dto.vo.Page;
import com.honger.expo.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
    private final static int pageSize = 3;

    @Autowired
    private NewService newService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseJSON allNews(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        List<NewsCategoryResponse> mapping = null;
        Page<List<NewsCategoryResponse>> rPage = null;

        try{
            PageHelper.startPage(Integer.valueOf(page), pageSize);
            mapping = newService.getAllNews();
            int totalNum = newService.getTotalNum();

            rPage = new Page<List<NewsCategoryResponse>>();
            rPage.setContent(mapping);
            rPage.setPageSize(pageSize);
            rPage.setTotalNum(totalNum);
            rPage.setPageNum(Integer.valueOf(page));
            if (totalNum - pageSize * Integer.valueOf(page) <= 0) {
                rPage.setLast(true);
            } else {
                rPage.setLast(false);
            }
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(rPage);
    }

    @ResponseBody
    @RequestMapping(value = "/sortedNews", method = RequestMethod.GET)
    public ResponseJSON newsCategory(@RequestParam("newsCategory") String newsCategory,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        if(newsCategory==null || newsCategory.equals("")){
            return ResponseJSON.error("请输入新闻分类参数");
        }
        List<NewsCategoryResponse> mapping = null;
        Page<List<NewsCategoryResponse>> rPage = null;

        try{
            PageHelper.startPage(Integer.valueOf(page), pageSize);
            mapping = newService.newsCategory(newsCategory);

            int totalNum = newService.getNewsCategoryNum(newsCategory);

            rPage = new Page<List<NewsCategoryResponse>>();
            rPage.setContent(mapping);
            rPage.setPageSize(pageSize);
            rPage.setTotalNum(totalNum);
            rPage.setPageNum(Integer.valueOf(page));
            if (totalNum - pageSize * Integer.valueOf(page) <= 0) {
                rPage.setLast(true);
            } else {
                rPage.setLast(false);
            }
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(rPage);
    }

    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseJSON allNewsCategory() {
        List<String> mapping = null;
        try{
            mapping = newService.allNewsCategory();
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(mapping);
    }

    @ResponseBody
    @RequestMapping(value = "/newsRelatedExpos/{id}", method = RequestMethod.GET)
    public ResponseJSON getRelationExhibitionByNewsId(@PathVariable("id") String id) {
        if(id==null || id.equals("")){
            return ResponseJSON.error("请输入新闻id");
        }
        List<ExhibitionSearchVO>  relationExhibitionByNewsId = null;
        try{
            relationExhibitionByNewsId = newService.getRelationExhibitionByNewsId(id);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(relationExhibitionByNewsId);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseJSON newsById(@PathVariable("id") String id) {
        if(id==null || id.equals("")){
            return ResponseJSON.error("请输入新闻id");
        }
        List<NewsCategoryResponse> mapping = null;
        try{
            mapping = newService.newsById(id);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(mapping);
    }
}