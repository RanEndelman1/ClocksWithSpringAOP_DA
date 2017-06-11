package com.springaop;

import java.util.List;

//In ProxyFactoryBean it contains 2 properties target and interceptorNames. 
//The instance of JavaFXApplication1 class will be considered as target object 
//and the instance of advisor class as interceptor. 
//We need to pass the advisor object as the list object.

public class ProxyFactoryBean {
	private Object target;
	private List interceptorNames;

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public List getInterceptorNames() {
		return interceptorNames;
	}

	public void setInterceptorNames(List interceptorNames) {
		this.interceptorNames = interceptorNames;
	}

}