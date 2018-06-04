package com.honger.expo.dto.vo;

import com.honger.expo.pojo.Exhibition;

/**
 * Created by chenjian on 2018/4/16.
 */
public class ExhibitionHomePage extends Exhibition {
    private String isChoice;
    private String isHot;
    private String isCarousel;

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getIsCarousel() {
        return isCarousel;
    }

    public void setIsCarousel(String isCarousel) {
        this.isCarousel = isCarousel;
    }
}
