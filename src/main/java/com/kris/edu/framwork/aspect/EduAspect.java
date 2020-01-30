package com.kris.edu.framwork.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EduAspect {

    @Pointcut("execution(public * com.kris.edu.app.user.controller.UserContoller.*(..)))")
    public void KrisCut(){

    }

    @Before("KrisCut()")
    public void doBefore(){
        System.out.println("before send to the controller");
    }

    @After("KrisCut()")
    public void doAfter(){
        System.out.println("after send to the controller");
    }


}
