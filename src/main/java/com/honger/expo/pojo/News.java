package com.honger.expo.pojo;

import java.util.Date;

/**
 * Created by chenjian on 2018/4/15.
 */
public class News {
    private String id;
    private Date createTime;
    private Date updateTime;
    //状态
    private Integer delete;
    //关联行业
    private String categoryId;
    //新闻内容
    private String content;
    //新闻来源
    private String origin;
    //新闻热度
    private Integer hot;
    //新闻热度排名
    private Integer hotLevel;
    //新闻标题
    private  String title;
    //新闻类别
    private String newsCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getHotLevel() {
        return hotLevel;
    }

    public void setHotLevel(Integer hotLevel) {
        this.hotLevel = hotLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delete=" + delete +
                ", categoryId='" + categoryId + '\'' +
                ", content='" + content + '\'' +
                ", origin='" + origin + '\'' +
                ", hot=" + hot +
                ", hotLevel=" + hotLevel +
                ", title='" + title + '\'' +
                ", newsCategory='" + newsCategory + '\'' +
                '}';
    }
}
