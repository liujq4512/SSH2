package com.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class MyPreResultListener implements PreResultListener{

	@Override
	public void beforeResult(ActionInvocation arg0, String arg1) {
		System.out.println("preResultListener before :"+ arg0.getClass().getName());
		System.out.println("返回的结果集是：" + arg1);
		
	}

}
