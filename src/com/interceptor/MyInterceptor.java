package com.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor{
	private String name;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("myInterceptor start time:"+ System.currentTimeMillis());
		System.out.println("name is:" + this.name);
		//Ìí¼Ó¼àÌýÆ÷
		invocation.addPreResultListener(new MyPreResultListener());
		String result = invocation.invoke();
		System.out.println("myInterceptor end time"+ System.currentTimeMillis());
		return result;
	}
	
}
