package com.honger.expo.dao;

import com.honger.expo.message.MessageEventPublisher;
import com.honger.expo.pojo.ExhibitionCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.UUID;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MessageSender {
    @Autowired
    private MessageEventPublisher messageEventPublisher;

    @Test
    public void messageSenderTest(){
        messageEventPublisher.publish();
    }
}

