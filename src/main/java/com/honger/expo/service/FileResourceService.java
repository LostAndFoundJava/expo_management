package com.honger.expo.service;

import com.honger.expo.dto.response.visa.VisaResponse;
import com.honger.expo.pojo.FileResource;
import com.honger.expo.pojo.RegionData;

import java.util.List;

public interface FileResourceService {
    FileResource getFileResourceById(String id);
}
