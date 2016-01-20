package com.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GetSpringContext implements ApplicationContextAware{
	//将beanFactory 以成员变量形式保存
	private ApplicationContext ctx;
	
	//@param arg0 创建bean实例的ApplicationContext
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.ctx = arg0;
		
	}
	//获取
	public ApplicationContext getContext(){
		return this.ctx;
	}
}
