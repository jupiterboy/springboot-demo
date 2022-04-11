package com.tcb.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
public class TimeItAspect {

    @Pointcut("@annotation(com.tcb.common.utils.TimeIt)")
    public void timeIt(){
    }

    @Around("timeIt()")
    public Object time(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        Signature signature = proceedingJoinPoint.getSignature();
        Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
        log.info("TimeIt: 方法 {} 耗时：{} ms", signature.getName(), (endTime - startTime));
        return result;
    }
}