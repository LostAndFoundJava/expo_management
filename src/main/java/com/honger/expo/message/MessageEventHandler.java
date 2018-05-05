package com.honger.expo.message;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by chenjian on 2018/5/5.
 */
@Component
public class MessageEventHandler implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println(event.toString());
    }

}
