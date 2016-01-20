package com.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class MyMethodInterceptor extends MethodFilterInterceptor{
	private final Log log = LogFactory.getLog(MyMethodInterceptor.class);
	
	private String name;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		if(log.isInfoEnabled()){
			log.info("//////////////////////////DEBUG: doIntercept////////////");
		}
		System.out.println("method interceptor start time :"+ System.currentTimeMillis());
		System.out.println("name is :"+name);
		String result = invocation.invoke();
		System.out.println("method interceptor end time :"+ System.currentTimeMillis());
		return result;
	}

}
