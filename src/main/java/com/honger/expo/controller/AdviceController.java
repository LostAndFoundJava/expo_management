package com.honger.expo.controller;

import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.Advice;
import com.honger.expo.service.AdviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/advice")
@Slf4j
public class AdviceController {
    @Autowired
    private AdviceService adviceService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseJSON insert(@RequestBody Advice advice) {
        String material = advice.getMaterial();
        if(material == null || material.trim().equals(""))
            return ResponseJSON.error("请传需要的资料");

        String mobileNo = advice.getMobileNo();
        if(mobileNo == null || mobileNo.trim().equals(""))
            return ResponseJSON.error("请传手机号码");

        try{
            adviceService.insert(advice);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok();
    }
}