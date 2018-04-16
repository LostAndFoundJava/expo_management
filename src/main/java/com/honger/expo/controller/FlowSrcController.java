package com.honger.expo.controller;


import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.FlowSrc;
import com.honger.expo.service.CategoryService;
import com.honger.expo.service.FlowSrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/flowSrc")
public class FlowSrcController {

    @Autowired
    private FlowSrcService flowSrcService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseJSON categories(@RequestBody FlowSrc flowSrc) {
        try{
            flowSrcService.insert(flowSrc);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok();
    }
}