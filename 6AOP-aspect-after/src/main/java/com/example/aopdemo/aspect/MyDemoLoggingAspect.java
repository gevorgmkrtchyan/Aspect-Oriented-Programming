package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @After("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFindAccountAdvice(JoinPoint joinPoint){
        //print out which method we are advising on
        String signature = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After(finally) on method: " + signature);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exception"
    )
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exception){

        //print out which method we are advising on
        String signature = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + signature);

        //log the exception
        System.out.println("\n=====>>> The exception is:" + exception);
    }

    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        //print method name
        String signature = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + signature);

        //print result
        System.out.println("\n=====>>> result is:" + result);

        //let's post-process the data

        //convert Account name to UPPERCASE
        convertAccountNameToUpperCase(result);
        System.out.println("\n=====>>> result is:" + result);
    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        for (Account account: result){
            account.setName(account.getName().toUpperCase());
        }
    }


    @Before("com.example.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {

        System.out.println("\n=====>>> Executing @Before advice on method");

        //display method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        //display method arguments
        Object[] args = joinPoint.getArgs();
        for (Object arguments : args) {
            System.out.println(arguments);
            if (arguments instanceof Account) {
                Account account = (Account) arguments;
                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }


}










