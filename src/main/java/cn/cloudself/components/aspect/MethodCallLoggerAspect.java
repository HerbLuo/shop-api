package cn.cloudself.components.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Ghosted on 2016/6/12.
 * 基于Spring aop（对每一个方法调用进行日志记录）
 */
//@Aspect
//@Component
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class MethodCallLoggerAspect {

//    @Autowired
    private Logger logger;

    @Pointcut("execution(public * cn.cloudself.controller.*Controller.*(..)) || execution(public * cn.cloudself.service.impl.*ServiceImpl.*(..)))")
    public void needLogMethod() {
    }

    @Before("needLogMethod()")
    public void logBefore(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getSimpleName();
        logger.debug("The method name is " + className + "." + methodName);
        logger.debug("The method " + methodName + " params are " + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(pointcut = "needLogMethod()", returning = "returnVal")
    public void log(JoinPoint jp, Object returnVal) {
        logger.debug("The method " + jp.getSignature().getName() + " return a [" + returnVal + "] value");
    }

}
