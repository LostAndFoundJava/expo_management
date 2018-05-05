package com.honger.expo.service.impl;

import com.honger.expo.dao.FileResourceMapper;
import com.honger.expo.dao.RegionDataMapper;
import com.honger.expo.dao.VisaMapper;
import com.honger.expo.dto.response.visa.VisaResponse;
import com.honger.expo.dto.vo.VisaVO;
import com.honger.expo.pojo.FileResource;
import com.honger.expo.pojo.RegionData;
import com.honger.expo.service.FileResourceService;
import com.honger.expo.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileResourceServiceImpl implements FileResourceService {
    @Autowired
    private FileResourceMapper fileResourceMapper;

    @Override
    public FileResource getFileResourceById(String id) {
        return fileResourceMapper.getFileResourceById(id);
    }
}
