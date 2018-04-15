package com.honger.expo.dto.vo;

/**
 * Created by chenjian on 2018/4/15.
 */
public class Page<T> {
    private int pageNum;
    private String pageSize;
    private boolean isLats;
    private int totalNum;
    private T content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isLats() {
        return isLats;
    }

    public void setLats(boolean lats) {
        isLats = lats;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
