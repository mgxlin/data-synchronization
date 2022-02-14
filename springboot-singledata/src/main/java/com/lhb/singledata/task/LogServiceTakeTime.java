package com.lhb.singledata.task;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lhb
 * @date 2022/2/14 10:38
 */
@Aspect
@Component
public class LogServiceTakeTime {

    private final static Logger log = LoggerFactory.getLogger(LogServiceTakeTime.class);

    @Pointcut("execution(* com.lhb.singledata.service.*.*(..))")
    public void performance(){
    }

    @Around("performance()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //记录起始时间
        long begin = System.currentTimeMillis();
        Object result = "";
        /** 执行目标方法 */
        try{
            result= joinPoint.proceed();
        }
        catch(Exception e){
            log.error("日志记录发生错误, errorMessage: {}", e.getMessage());
        }
        finally{
            /** 记录操作时间 */
            long took = System.currentTimeMillis() - begin;
            if (took >= 1000) {
                log.error("Service 执行时间为: {}秒", took/1000);
            } else{
                log.warn("Service 执行时间为: {}毫秒", took);
            }
        }
        return result;
    }

    @Before("performance()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        log.info("开始同步");
    }

    @AfterReturning(returning = "ret", pointcut = "performance()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("同步结束");
    }

}
