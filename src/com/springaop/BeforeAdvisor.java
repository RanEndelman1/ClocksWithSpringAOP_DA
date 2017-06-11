package com.springaop;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//Before Advisor class is executed before the actual method call.

//MethodBeforeAdvice interface extends the BeforeAdvice interface.

public class BeforeAdvisor implements MethodBeforeAdvice {
	UserJDBCTemplate userJDBCTemplate;
	ApplicationContext context;

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {

		System.out.println("additional concern before actual logic");
		/* The DB xml file */
		context = new ClassPathXmlApplicationContext("Beans.xml");

		userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

		userJDBCTemplate.insertLogInLog(InetAddress.getLocalHost().toString(),
				new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
				new SimpleDateFormat("HH.mm.ss").format(new Date()));
	}
}