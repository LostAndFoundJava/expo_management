package com.honger.expo.controller;

import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.utils.CacheUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by chenjian on 2018/5/12.
 */
@Controller
@RequestMapping(value = "/clearing")
public class ClearCacheController {
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseJSON clearing(){
        CacheUtils.getCacheSingleton().getConcurrentHashMap().clear();
        return ResponseJSON.ok("clearing success!!");
    }
}
