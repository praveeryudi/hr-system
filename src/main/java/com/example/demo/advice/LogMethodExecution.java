package com.example.demo.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogMethodExecution {

    @Around("@annotation(com.example.demo.annotation.LogExecutionTime)")
    public Object logMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Time to execute method " + pjp.getSignature() + " :: " + (endTime - startTime));
        return retVal;
    }
}
