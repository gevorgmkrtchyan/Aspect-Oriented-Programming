package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        //call method to find accounts
        List<Account> accounts = theAccountDAO.findAccounts();
        System.out.println("\n\n Main Program: After Returning");
        System.out.println("------");
        System.out.println(accounts);
        System.out.println("\n");

        // close the context
        context.close();
    }

}


