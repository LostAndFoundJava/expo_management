package com.honger.expo.controller;

import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.FlowSrc;
import com.honger.expo.service.FlowSrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/flowSrc")
public class FlowSrcController {

    @Autowired
    private FlowSrcService flowSrcService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseJSON insert(@RequestBody FlowSrc flowSrc) {
        HashMap<String,String> result = new HashMap<>();
        String s = "";
        try{
            s = flowSrcService.insert(flowSrc);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        if(s.equals(""))
            return ResponseJSON.error("插入失败！！");
        else{
            result.put("id",s);
            return ResponseJSON.ok(result);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseJSON update(@RequestBody FlowSrc flowSrc) {
        try{
            flowSrcService.update(flowSrc);
        }catch (Exception e){
            return ResponseJSON.error("更新失败！！");
        }
            return ResponseJSON.ok();
    }
}