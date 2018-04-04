package com.honger.expo.pojo;

import java.util.Date;

public class ExhibitionDetail {
    private String id;

    private String exhibitionId;

    private Boolean delete;

    private Date createTime;

    private Date updateTime;

    public ExhibitionDetail() {
    }

    public ExhibitionDetail(String id, String exhibitionId, Boolean delete, Date createTime, Date updateTime) {
        this.id = id;
        this.exhibitionId = exhibitionId;
        this.delete = delete;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(String exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
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

    @Override
    public String toString() {
        return "ExhibitionDetail{" +
                "id='" + id + '\'' +
                ", exhibitionId='" + exhibitionId + '\'' +
                ", delete=" + delete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}