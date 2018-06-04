package com.honger.expo.dto.vo;

import com.honger.expo.pojo.Exhibition;

import java.util.Objects;

/**
 * Created by chenjian on 2018/4/15.
 */
public class ExhibitionSearchVO{
    private String categroy;
    private String countryName;
    private String countryEnName;
    private String countryPinyin;
    private String cityName;
    private String cityEnName;
    private String cityPinyin;

    private String isChoice;
    private String isCarousel;
    private String isHot;

    private String clickCount;

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }

    public String getIsCarousel() {
        return isCarousel;
    }

    public void setIsCarousel(String isCarousel) {
        this.isCarousel = isCarousel;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    private Exhibition exhibition;

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public String getCountryEnName() {
        return countryEnName;
    }

    public void setCountryEnName(String countryEnName) {
        this.countryEnName = countryEnName;
    }

    public String getCountryPinyin() {
        return countryPinyin;
    }

    public void setCountryPinyin(String countryPinyin) {
        this.countryPinyin = countryPinyin;
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExhibitionSearchVO that = (ExhibitionSearchVO) o;
        return Objects.equals(categroy, that.categroy) &&
                Objects.equals(countryName, that.countryName) &&
                Objects.equals(countryEnName, that.countryEnName) &&
                Objects.equals(countryPinyin, that.countryPinyin) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(cityEnName, that.cityEnName) &&
                Objects.equals(cityPinyin, that.cityPinyin) &&
                Objects.equals(isChoice, that.isChoice) &&
                Objects.equals(isCarousel, that.isCarousel) &&
                Objects.equals(isHot, that.isHot) &&
                Objects.equals(clickCount, that.clickCount) &&
                Objects.equals(exhibition, that.exhibition);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categroy, countryName, countryEnName, countryPinyin, cityName, cityEnName, cityPinyin, isChoice, isCarousel, isHot, clickCount, exhibition);
    }
}
