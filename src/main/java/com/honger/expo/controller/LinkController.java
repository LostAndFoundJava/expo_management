package com.honger.expo.controller;

import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.Link;
import com.honger.expo.service.AboutUsService;
import com.honger.expo.service.LinkService;
import com.honger.expo.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping(value = "/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseJSON getAboutUs(){
        List<Link> links = null;
        try{
            CacheUtils cacheSingleton = CacheUtils.getCacheSingleton();
            ConcurrentHashMap<String, Object> concurrentHashMap = cacheSingleton.getConcurrentHashMap();
            boolean link = concurrentHashMap.containsKey("links");
            if(link){
                links = (List<Link>)concurrentHashMap.get("links");
            }else {
                links = linkService.getAllLink();
                concurrentHashMap.put("links",links);
            }
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(links);
    }

}