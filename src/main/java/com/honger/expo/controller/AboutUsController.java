package com.honger.expo.controller;

import com.honger.expo.annotation.CountAnnotation;
import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.service.AboutUsService;
import com.honger.expo.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping(value = "/aboutUs")
public class AboutUsController {
    @Autowired
    private AboutUsService aboutUsService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseJSON getAboutUs(){
        List<AboutUsResponse> aboutUs = null;
        try{
            aboutUs = aboutUsService.getAboutUs();
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(aboutUs);
    }

    @ResponseBody
    @RequestMapping(value = "/types",method = RequestMethod.GET)
    public ResponseJSON getAboutUsByTypes(){
        List<AboutUsResponse> aboutUs = null;
        try{
            ConcurrentHashMap<String, Object> concurrentHashMap = CacheUtils.getCacheSingleton().getConcurrentHashMap();
            boolean b = concurrentHashMap.containsKey("aboutUs.types");
            if(b){
                aboutUs = (List<AboutUsResponse>)concurrentHashMap.get("aboutUs.types");
            }else{
                aboutUs = aboutUsService.getAboutUsByTypes();
                concurrentHashMap.put("aboutUs.types",aboutUs);
            }
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(aboutUs);
    }
}