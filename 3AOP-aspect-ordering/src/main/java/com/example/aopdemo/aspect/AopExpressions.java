package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class AopExpressions {
    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    //create pointcut for getter method
    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    public void getter() {
    }

    //create pointcut for setter method
    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    public void setter() {
    }

    //create pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }


}
