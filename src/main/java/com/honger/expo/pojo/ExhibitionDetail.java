package com.honger.expo.pojo;

import java.util.Date;

public class ExhibitionDetail {
    private String id;

    private String exhibitionId;

    private Boolean delete;

    private Date createTime;

    private Date updateTime;

    private Date applyEndTime;

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

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExhibitionDetail)) return false;

        ExhibitionDetail that = (ExhibitionDetail) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getExhibitionId() != null ? !getExhibitionId().equals(that.getExhibitionId()) : that.getExhibitionId() != null)
            return false;
        if (getDelete() != null ? !getDelete().equals(that.getDelete()) : that.getDelete() != null) return false;
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null)
            return false;
        if (getUpdateTime() != null ? !getUpdateTime().equals(that.getUpdateTime()) : that.getUpdateTime() != null)
            return false;
        return getApplyEndTime() != null ? getApplyEndTime().equals(that.getApplyEndTime()) : that.getApplyEndTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getExhibitionId() != null ? getExhibitionId().hashCode() : 0);
        result = 31 * result + (getDelete() != null ? getDelete().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        result = 31 * result + (getApplyEndTime() != null ? getApplyEndTime().hashCode() : 0);
        return result;
    }
}