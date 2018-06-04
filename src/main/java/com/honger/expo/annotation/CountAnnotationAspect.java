package com.honger.expo.annotation;

import com.honger.expo.service.ClickCountService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
            //首页点击次数统计
            if(args.length == 0){
                   clickCountService.insertOrUpdateClickCount("home",2);
            }else {
                //详情页点击次数==》exhibiton和news
                clickedId = (String) args[0];

                String name = joinPoint.getSignature().getName();
                if(name.contains("Exhibition") || name.contains("exhibition"))
                    clickCountService.insertOrUpdateClickCount(clickedId,0);
                else if(name.contains("News") || name.contains("news"))
                    clickCountService.insertOrUpdateClickCount(clickedId,1);
            }
        }catch (Exception e){
            log.error(clickedId+":"+"增加访问次数失败！！！"+e.getMessage());
        }
    }

}
