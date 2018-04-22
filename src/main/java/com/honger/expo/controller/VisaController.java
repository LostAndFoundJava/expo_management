package com.honger.expo.controller;


import com.honger.expo.dto.response.news.NewsCategoryResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.response.visa.VisaResponse;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.NewService;
import com.honger.expo.service.VisaService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = "/visa")
public class VisaController {
    @Autowired
    private VisaService visaService;

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public ResponseEntity<String> download() throws IOException {
        String path="/Users/chenjian/Desktop/集团简介.doc";
        File file=new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String("集团简介.doc".getBytes("UTF-8"),
                "iso-8859-1");//为了解决中文名称乱码问题

        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<String>(FileUtils.readFileToString(file,"iso-8859-1"),
                headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ResponseJSON getRegionCountryByContinent(String continent) throws IOException {
        List<RegionData> regionCountryByContinent = null;
        try{
            regionCountryByContinent = visaService.getRegionCountryByContinent(continent);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(regionCountryByContinent);
    }

    @ResponseBody
    @RequestMapping(value = "/countries/{countryId}", method = RequestMethod.GET)
    public ResponseJSON getVisaByCountryId(@PathVariable("countryId") String countryId) throws IOException {
        VisaResponse v = null;
        try{
             v = visaService.getVisaByCountryId(countryId);
        }catch (Exception e){
            return ResponseJSON.error();
        }
        return ResponseJSON.ok(v);
    }
}
