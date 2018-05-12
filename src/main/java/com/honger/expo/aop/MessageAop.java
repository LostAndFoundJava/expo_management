package com.honger.expo.aop;

/**
 * Created by chenjian on 2018/5/5.
 */

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by chenjian on 2018/5/2.
 */
@Aspect
//@Component
@Slf4j
public class MessageAop {
    //切入点
    @Pointcut(("execution(* com.honger.expo.controller.FlowSrcController.insert(..))"))
    private void pointcut() {

    }

    @AfterReturning(value = "pointcut()")
    public void afterReturn(JoinPoint joinPoint) {

    }

}
