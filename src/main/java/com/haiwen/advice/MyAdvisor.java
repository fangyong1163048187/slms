package com.haiwen.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*@Component
@Aspect*/
public class MyAdvisor {
    /*@Around("com.haiwen.service.impl.EmployeeServiseImpl.truncate()")*/
    public void surrounding(ProceedingJoinPoint point)throws java.lang.Throwable{
        //获取切点方法返回值
        Integer result = (Integer)point.proceed();
        //切点执行之后执行的语句，相当于后置通知
        Logger logger = Logger.getLogger(MyAdvisor.class);
        if(result>0){
            logger.info("成功清空所有数据");
        }else{
            logger.info("清空数据失败");
        }
    }
}
