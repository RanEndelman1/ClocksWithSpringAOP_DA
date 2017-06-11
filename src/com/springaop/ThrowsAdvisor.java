package com.springaop;  
import org.springframework.aop.ThrowsAdvice;  

//Throws Advisor is executed if actual method throws exception.

//ThrowsAdvice interface extends the AfterAdvice interface.

public class ThrowsAdvisor implements ThrowsAdvice
{  
    public void afterThrowing(Exception ex){  
        System.out.println("additional concern if exception occurs");  
    }  
}  