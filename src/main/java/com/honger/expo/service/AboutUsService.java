package com.honger.expo.service;

import com.honger.expo.dto.response.aboutUs.AboutUsResponse;

import java.util.List;

public interface AboutUsService {
    List<AboutUsResponse> getAboutUs();

    List<AboutUsResponse> getAboutUsByTypes();
}
