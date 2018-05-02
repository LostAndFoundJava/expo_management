package com.honger.expo.annotation;

import com.honger.expo.pojo.Exhibition;
import com.honger.expo.service.ExhibitionCountService;
import com.honger.expo.service.ExhibitionService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chenjian on 2018/5/2.
 */
@Aspect
@Component
@Slf4j
public class CountAnnotationAspect {
    @Autowired
    private ExhibitionCountService exhibitionCountService;

    //切入点
    @Pointcut(value = "@annotation(com.honger.expo.annotation.CountAnnotation)")
    private void pointcut() {

    }

    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        String exhibitionId = "";
        try {
            Object[] args = joinPoint.getArgs();
            exhibitionId = (String) args[0];
            exhibitionCountService.insertOrUpdateExhibitionCount(exhibitionId);
        }catch (Exception e){
            log.error(exhibitionId+":"+"增加访问次数失败！！！"+e.getMessage());
        }
    }

}
