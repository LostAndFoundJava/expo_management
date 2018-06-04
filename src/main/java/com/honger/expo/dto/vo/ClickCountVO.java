package com.honger.expo.dto.vo;

import com.honger.expo.pojo.ClickCount;
import com.honger.expo.pojo.Exhibition;

/**
 * Created by chenjian on 2018/5/5.
 */
public class ClickCountVO{
    private ClickCount clickCount;
    private Object clicked;

    public ClickCount getClickCount() {
        return clickCount;
    }

    public void setClickCount(ClickCount clickCount) {
        this.clickCount = clickCount;
    }

    public Object getClicked() {
        return clicked;
    }

    public void setClicked(Object clicked) {
        this.clicked = clicked;
    }
}
