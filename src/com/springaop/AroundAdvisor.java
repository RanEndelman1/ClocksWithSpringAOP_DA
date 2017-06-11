package com.springaop;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//Around Advisor it is executed before and after the actual method call.
//MethodInterceptor interface extends the Interceptor interface. It is used in around advice.

public class AroundAdvisor implements MethodInterceptor {
	UserJDBCTemplate userJDBCTemplate;
	ApplicationContext context;

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		/* Before code */
		Object obj;
		System.out.println("additional concern before actual logic");
		String time1 = new SimpleDateFormat("HH:mm:ss").format(new Date());
		
		obj = mi.proceed();
		
		/* After code */
		System.out.println("additional concern after actual logic");
		String time2 = new SimpleDateFormat("HH:mm:ss").format(new Date());
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		long difference = (date2.getTime() - date1.getTime()) / 1000; 
		String delta = "" + difference + " Seconds";
		userJDBCTemplate.insertUsageTimeLog(InetAddress.getLocalHost().toString(),
				new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
				new SimpleDateFormat("HH.mm.ss").format(new Date()), delta);
		return obj;
	}

}