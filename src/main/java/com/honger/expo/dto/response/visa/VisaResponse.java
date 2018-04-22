package com.honger.expo.dto.response.visa;

import com.honger.expo.pojo.RegionData;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjian on 2018/4/22.
 */
public class VisaResponse {
    private RegionData continent;
    private RegionData country;
    List<Map<String,String>> visa;

    private String visaInfo;
    private Date createTime;
    private Date updateTime;

    public RegionData getContinent() {
        return continent;
    }

    public void setContinent(RegionData continent) {
        this.continent = continent;
    }

    public RegionData getCountry() {
        return country;
    }

    public void setCountry(RegionData country) {
        this.country = country;
    }

    public List<Map<String, String>> getVisa() {
        return visa;
    }

    public void setVisa(List<Map<String, String>> visa) {
        this.visa = visa;
    }

    public String getVisaInfo() {
        return visaInfo;
    }

    public void setVisaInfo(String visaInfo) {
        this.visaInfo = visaInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
