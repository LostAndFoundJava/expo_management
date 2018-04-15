package com.honger.expo.dto.vo;

import com.honger.expo.pojo.Exhibition;

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
}
