package com.honger.expo.dto.vo;

import com.honger.expo.pojo.ClickCount;
import com.honger.expo.pojo.Exhibition;

/**
 * Created by chenjian on 2018/5/5.
 */
public class ClickCountExhibition{
    private ClickCount clickCount;
    private Exhibition exhibition;

    public ClickCount getClickCount() {
        return clickCount;
    }

    public void setClickCount(ClickCount clickCount) {
        this.clickCount = clickCount;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }
}
