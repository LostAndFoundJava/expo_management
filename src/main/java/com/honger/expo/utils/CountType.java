package com.honger.expo.utils;

import com.honger.expo.dao.ExhibitionMapper;
import com.honger.expo.dao.NewsMapper;

/**
 * Created by chenjian on 2018/5/6.
 */
public enum CountType {
    exhibition("0"),news("1");

    private String str;

    CountType(String str){
        this.str = str;
    }

    public String getType(){
        return str;
    }
}


