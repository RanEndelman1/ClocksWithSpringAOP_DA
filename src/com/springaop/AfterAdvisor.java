package com.springaop;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//After Advisor it is executed after the actual method call. If method returns a value, it is executed after returning value.
//AfterReturningAdvice interface extends the AfterAdvice interface.

public class AfterAdvisor implements AfterReturningAdvice {
	UserJDBCTemplate userJDBCTemplate;
	ApplicationContext context;

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

		System.out.println("additional concern after returning advice");

		/* The DB xml file */
		context = new ClassPathXmlApplicationContext("Beans.xml");

		userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

		userJDBCTemplate.insertLogOutLog(InetAddress.getLocalHost().toString(),
				new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
				new SimpleDateFormat("HH.mm.ss").format(new Date()));
	}

}