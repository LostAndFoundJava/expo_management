package com.honger.expo.controller;

import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.Link;
import com.honger.expo.service.AboutUsService;
import com.honger.expo.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseJSON getAboutUs(){
        List<Link> Links = null;
        try{
            Links = linkService.getAllLink();
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(Links);
    }

}