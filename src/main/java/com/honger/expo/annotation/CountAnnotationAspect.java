package com.honger.expo.annotation;

import com.honger.expo.pojo.ClickCount;
import com.honger.expo.service.ClickCountService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chenjian on 2018/5/2.
 */
@Aspect
@Component
@Slf4j
public class CountAnnotationAspect {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ClickCountService clickCountService;

    //切入点
    @Pointcut(value = "@annotation(com.honger.expo.annotation.CountAnnotation)")
    private void pointcut() {

    }

    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        String clickedId = "";
        try {
            Object[] args = joinPoint.getArgs();
            clickedId = (String) args[0];
//            Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
//            String name = joinPoint.getSignature().getName();
//            String serviceName = "";
//            for(Method m : methods){
//                if(m.getName().equals(name)){
//                    serviceName = m.getAnnotation(CountAnnotation.class).seriveName();
//                    break;
//                }
//            }

//            if(serviceName.equalsIgnoreCase("ClickCountService")){
//                ClickCountService bean = (ClickCountService)applicationContext.getBean(
//                        serviceName.substring(0,1).toLowerCase() +serviceName.substring(1)+"Impl");
//
//                bean.insertOrUpdateExhibitionCount(exhibitionId);
//            }
            String name = joinPoint.getSignature().getName();
            if(name.contains("Exhibition") || name.contains("exhibition"))
                clickCountService.insertOrUpdateExhibitionCount(clickedId,0);
            else if(name.contains("News") || name.contains("news"))
                clickCountService.insertOrUpdateExhibitionCount(clickedId,1);
        }catch (Exception e){
            log.error(clickedId+":"+"增加访问次数失败！！！"+e.getMessage());
        }
    }

}
