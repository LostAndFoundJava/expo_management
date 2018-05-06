package com.honger.expo.dto;

/**
 * Created by chenjian on 2018/5/6.
 */
/*
*   `id` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-删除',
  `category_id` varchar(32) NOT NULL COMMENT '业行id',
  `exhibition_id` varchar(32) NOT NULL COMMENT '关联的展会id',
  `is_hot` tinyint(3) DEFAULT '0' COMMENT '是否推荐-0-未推荐，1-推荐',
  `is_carousel` tinyint(3) DEFAULT '0' COMMENT '是否首页轮播-0-不是，1-是',
  `is_choice` tinyint(3) DEFAULT '0' COMMENT '行业中展会精选(1-精选，0-非精选)',
  `homepage_id` varchar(32) NOT NULL COMMENT 'home_page_hot.id',*/
public class HomePageConfig {
    private String isHot;
    private String isCarousel;
    private String isChoice;

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

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }
}

