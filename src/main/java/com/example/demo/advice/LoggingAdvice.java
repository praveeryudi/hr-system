package com.example.demo.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    private Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Around("@annotation(com.example.demo.annotation.LogMethodExecution)")
    public Object logMethod(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper objMapper = new ObjectMapper();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        log.info("Method invoked :: " + className + " : " + methodName + " with arguments" + " : " + objMapper.writeValueAsString(args));
        Object retVal = pjp.proceed();
        log.info("Response" + " : " + objMapper.writeValueAsString(retVal));
        return retVal;
    }
}
