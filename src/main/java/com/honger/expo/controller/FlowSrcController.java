package com.honger.expo.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.honger.expo.dto.response.exhibition.ExhibitionDetailResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.FlowSrc;
import com.honger.expo.service.ExhibitionService;
import com.honger.expo.service.FlowSrcService;
import com.honger.expo.utils.MessageSendUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
@RequestMapping(value = "/api/flowSrc")
@Slf4j
public class FlowSrcController {
    @Autowired
    private FlowSrcService flowSrcService;

    //    @Autowired
//    private MessageEventPublisher mep;
    @Autowired
    private ExhibitionService exhibitionService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseJSON insert(@RequestBody FlowSrc flowSrc) {
        HashMap<String, String> result = new HashMap<>();
        String s = "";
        String mobileNo = flowSrc.getMobileNo();
        String exhibitionId = flowSrc.getExhibition();

        if(mobileNo == null || exhibitionId == null)
            return ResponseJSON.error("号码或者展会不存在");

        Exhibition exhibition = exhibitionService.getExhibitionById(exhibitionId);

        if(exhibition == null)
            return ResponseJSON.error("不存在的展会");


        if((new Date().compareTo(exhibition.getApplyEndTime())) == -1){
            return ResponseJSON.error("超过了展会预定的截止的时间");
        }


        boolean f = flowSrcService.isMobileUnique(mobileNo, exhibitionId);
        if (f) {
            return ResponseJSON.error("此号码已预订过当前展会");
        }


        try {
            s = flowSrcService.insert(flowSrc);
        } catch (Exception e) {
            return ResponseJSON.error();
        }

        if (s.equals("")) {
            return ResponseJSON.error("插入失败！！");
        } else {
            result.put("id", s);
            try {
                LinkedHashMap<String, String> lhm = getStringStringLinkedHashMap(exhibitionId);

                String json = JSONObject.toJSON(lhm).toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ResponseJSON responseJSON = null;
                        try {
                            responseJSON = MessageSendUtil.sendMessage(mobileNo, json);
                            if (!responseJSON.getCode()) {
                                Thread.sleep(5000);
                                ResponseJSON responseJSON1 = MessageSendUtil.sendMessage(mobileNo, json);
                                log.error("短信发送失败"+responseJSON.getMsg()+"手机号码："+flowSrc.getMobileNo());
                            }
                        } catch (ClientException e) {
                            log.error(e.toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return ResponseJSON.ok(result);
        }
    }

    private LinkedHashMap<String, String> getStringStringLinkedHashMap(String exhibitionId) throws InvocationTargetException, IllegalAccessException {
        ExhibitionDetailResponse detail = exhibitionService.getDetail(exhibitionId);
        String cityName = detail.getExhibition().getCityName();
        String countryName = detail.getExhibition().getCountryName();
        String location = detail.getExhibition().getExhibition().getLocation();
        Date startTime = detail.getExhibition().getExhibition().getStartTime();
        Date endTime = detail.getExhibition().getExhibition().getEndTime();
        String name = detail.getExhibition().getExhibition().getTitle();

        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        if(name.length()<=20){
            lhm.put("name", name);
//            lhm.put("name1","");
        }else {
            lhm.put("name", name.substring(0,20));
//            lhm.put("name1",name.substring(20));
        }
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(startTime);//设置当前日期
        int month = calendar.get(Calendar.MONTH) + 1;
        lhm.put("startTime", calendar.get(Calendar.YEAR) + "." + month
                + "." + calendar.get(Calendar.DATE));
        calendar.setTime(endTime);//设置当前日期
        month = calendar.get(Calendar.MONTH) + 1;
        lhm.put("endTime", calendar.get(Calendar.YEAR) + "." + month
                + "." + calendar.get(Calendar.DATE));
        //lhm.put("countryName", countryName);
        //lhm.put("cityName", cityName);
        if(location.length()<=20){
            lhm.put("location", location);
//            lhm.put("location1","");
        }else {
            lhm.put("location", location.substring(0,20));
//            lhm.put("location1",location.substring(20));
        }
        return lhm;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseJSON update(@RequestBody FlowSrc flowSrc) {
        try {
            flowSrcService.update(flowSrc);
        } catch (Exception e) {
            return ResponseJSON.error("更新失败！！");
        }
        return ResponseJSON.ok();
    }
}