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
}
