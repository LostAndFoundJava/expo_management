package com.honger.expo.message;

import org.springframework.context.ApplicationEvent;

/**
 * Created by chenjian on 2018/5/5.
 */
public class MessageEvent extends ApplicationEvent{
    public MessageEvent(Object source) {
        super(source);
    }

    public String toString(){

        return "My Message Event";
    }
}
