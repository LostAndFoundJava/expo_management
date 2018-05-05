package com.honger.expo.controller;

import com.honger.expo.dto.response.status.ResponseJSON;
import com.honger.expo.dto.response.visa.VisaResponse;
import com.honger.expo.pojo.FileResource;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.FileResourceService;
import com.honger.expo.service.VisaService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/visa")
public class VisaController {
    @Autowired
    private VisaService visaService;

    @Autowired
    private FileResourceService fileResourceService;

    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> download(@PathVariable("id") String id) throws IOException {
        FileResource fileResourceById = fileResourceService.getFileResourceById(id);
        File file=new File(fileResourceById.getFileUrl());
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String(fileResourceById.getFileName().getBytes("UTF-8"),
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
