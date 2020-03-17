package com.example.demo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodExecution {

    private Logger logger = LoggerFactory.getLogger(LogMethodExecution.class);

    @Around("@annotation(com.example.demo.annotation.LogExecutionTime)")
    public Object logMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Time to execute method " + pjp.getSignature() + " :: " + (endTime - startTime));
        return retVal;
    }
}
